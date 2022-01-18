/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HONGNHAT
 */
public class EmployeeService {

    private final AccountService accountService = new AccountService();

    //Lấy danh sách tất cả nhân viên
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees, accounts "
                    + "WHERE employees.AccountID=accounts.AccountID");
            while (rs.next()) {
                Account account = new Account(rs.getString("AccountID"),
                        rs.getString("Username"), rs.getString("Password"),
                        Active.valueOf(rs.getString("Active")),
                        Role.valueOf(rs.getString("Role")));
                Employee employee = new Employee(rs.getString("EmployeeID"),
                        rs.getString("FullName"), Gender.valueOf(rs.getString("Gender")),
                        rs.getObject("BirthDate", LocalDate.class), rs.getString("IdentityCard"),
                        rs.getString("Phone"), rs.getString("Address"),
                        rs.getObject("HireDate", LocalDate.class), account);
                employees.add(employee);
            }
        }
        return employees;
    }

    //Thêm nhân viên, tài khoản
    public boolean addEmployee(Employee employee, Account account) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO employees(EmployeeID, FullName, BirthDate, "
                            + "Gender, IdentityCard, Phone, Address, "
                            + "HireDate, AccountID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, employee.getEmployeeID());
            ps.setString(2, employee.getFullName());
            ps.setObject(3, employee.getBirthDate());
            ps.setString(4, employee.getGender().name());
            ps.setString(5, employee.getIdentityCard());
            ps.setString(6, employee.getPhone());
            ps.setString(7, employee.getAddress());
            ps.setObject(8, employee.getHireDate());
            ps.setString(9, account.getAccountID());

            accountService.addAccount(account); //Thêm account
            ps.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Cập nhật: tên, địa chỉ
    public void updateEmployee(Employee employee, Account account) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);
//            as.addAccount(account);

            PreparedStatement ps = connection.prepareStatement("UPDATE employees "
                    + "SET FullName = ?, BirthDate = ?, Gender = ?, IdentityCard = ?, Phone = ?, "
                    + "Address = ?, HireDate = ? WHERE EmployeeID = ?");
            ps.setString(1, employee.getFullName());
            ps.setObject(2, employee.getBirthDate());
            ps.setString(3, employee.getGender().name());
            ps.setString(4, employee.getIdentityCard());
            ps.setString(5, employee.getPhone());
            ps.setString(6, employee.getAddress());
            ps.setObject(7, employee.getHireDate());
            ps.setString(8, employee.getEmployeeID());

            ps.executeUpdate();
            connection.commit();
        }
    }

    //Xóa nhân viên
    public void deleteEmployee(String employeeID, String accountID) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("DELETE FROM employees WHERE EmployeeID=?");
            ps.setString(1, employeeID);

            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM accounts WHERE AccountID=?");
            ps1.setString(1, accountID);

            ps.execute();
            ps1.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeByID(String id) {
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM employees WHERE EmployeeID=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account account = accountService.getAccountByID(rs.getString("AccountID"));
                return new Employee(rs.getString("EmployeeID"),
                        rs.getString("FullName"), Gender.valueOf(rs.getString("Gender")),
                        rs.getObject("BirthDate", LocalDate.class),
                        rs.getString("IdentityCard"), rs.getString("Phone"),
                        rs.getString("Address"), rs.getObject("HireDate", LocalDate.class), account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEmployeeByAccountID(String id) {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees WHERE AccountID=" + id);
            if (rs.next()) {
                Account account = accountService.getAccountByID(rs.getString("AccountID"));
                return new Employee(rs.getString("EmployeeID"),
                        rs.getString("FullName"), rs.getObject("Gender", Gender.class),
                        rs.getObject("BirthDate", LocalDate.class),
                        rs.getString("IdentityCard"), rs.getString("Phone"),
                        rs.getString("Address"), rs.getObject("HireDate", LocalDate.class), account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Employee> getEmployeesByName(String name) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement statement = c.prepareStatement("SELECT * FROM employees, accounts " +
                    "WHERE employees.AccountID = accounts.AccountID AND FullName like concat('%', ?, '%')");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getString("AccountID"), rs.getString("Username"),
                        rs.getString("Password"), Active.valueOf(rs.getString("Active")),
                        Role.valueOf(rs.getString("Role")));
                Employee employee = new Employee(rs.getString("EmployeeID"),
                        rs.getString("FullName"), Gender.valueOf(rs.getString("Gender")),
                        rs.getObject("BirthDate", LocalDate.class),
                        rs.getString("IdentityCard"), rs.getString("Phone"),
                        rs.getString("Address"), rs.getObject("HireDate", LocalDate.class), account);
                employees.add(employee);
            }
        }
        return employees;
    }

    public List<Employee> getEmployeesByName(Gender gender) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement statement = c.prepareStatement("SELECT * FROM employees, accounts " +
                    "WHERE employees.AccountID = accounts.AccountID AND Gender = ?");
            statement.setString(1, gender.name());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getString("AccountID"), rs.getString("Username"),
                        rs.getString("Password"), Active.valueOf(rs.getString("Active")),
                        Role.valueOf(rs.getString("Role")));
                Employee employee = new Employee(rs.getString("EmployeeID"),
                        rs.getString("FullName"), Gender.valueOf(rs.getString("Gender")),
                        rs.getObject("BirthDate", LocalDate.class),
                        rs.getString("IdentityCard"), rs.getString("Phone"),
                        rs.getString("Address"), rs.getObject("HireDate", LocalDate.class), account);
                employees.add(employee);
            }
        }
        return employees;
    }

    //Kiểm tra thông tin tài khoản đã lưu vào CSDL chưa
    public boolean checkEmployee(String employeeID, String accountID) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM employees WHERE EmployeeID = ? AND AccountID = ?");
            ps.setString(1, employeeID);
            ps.setString(2, accountID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }
}
