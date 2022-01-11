/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.User;
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
 *
 * @author HONGNHAT
 */
public class EmployeeService {

    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                User e = new Employee(rs.getString("EmployeeID"),
                        rs.getObject("HireDate", LocalDate.class), rs.getString("FullName"),
                        rs.getObject("BirthDate", LocalDate.class),
                        Gender.valueOf(rs.getString("Gender")),
                        rs.getString("IdentityCard"), rs.getString("Address"),
                        rs.getString("Phone"), rs.getString("AccountID"));
                employees.add((Employee) e);
            }
            s.close();
            c.close();
        }
        return employees;
    }

    public void addAccount(Employee employee, Account account) {

        try (Connection connection = JdbcUtils.getConnection()) {
            AccountService as = new AccountService();
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
            ps.setString(9, employee.getAccountID());

            as.addAccount(account);
            ps.executeUpdate();

            connection.commit();

            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addEmployee(Employee e) {

        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employees(EmployeeID, FullName, BirthDate, "
                    + "Gender, IdentityCard, Phone, Address, "
                    + "HireDate, AccountID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, e.getEmployeeID());
            preparedStatement.setString(2, e.getFullName());
            preparedStatement.setObject(3, e.getBirthDate());
            preparedStatement.setString(4, e.getGender().name());
            preparedStatement.setString(5, e.getIdentityCard());
            preparedStatement.setString(6, e.getPhone());
            preparedStatement.setString(7, e.getAddress());
            preparedStatement.setObject(8, e.getHireDate());
            preparedStatement.setString(9, e.getAccountID());

            preparedStatement.executeUpdate();

            connection.commit();

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEmployee(Employee employee, Account account) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            AccountService as = new AccountService();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE employees "
                    + "WHERE EmployeeID = ?, FullName = ?, BirthDate = ?, Gender = ?, IdentityCard = ?, Phone = ?, Address = ?, "
                    + "HireDate, AccountID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, employee.getEmployeeID());
            ps.setString(2, employee.getFullName());
            ps.setObject(3, employee.getBirthDate());
            ps.setString(4, employee.getGender().name());
            ps.setString(5, employee.getIdentityCard());
            ps.setString(6, employee.getPhone());
            ps.setString(7, employee.getAddress());
            ps.setObject(8, employee.getHireDate());
            ps.setString(9, employee.getAccountID());

            as.addAccount(account);
            ps.executeUpdate();

            connection.commit();

            ps.close();
        }
    }

    public void deleteEmployee(String employee, String account) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);
            AccountService as = new AccountService();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM employees "
                    + "WHERE EmployeeID = ?")) {
                preparedStatement.setString(1, employee);

                as.deleteAccount(account);
                preparedStatement.executeUpdate();

                connection.commit();
            }
        }
    }

    public Employee getEmployeeByID(String id) throws SQLException {

        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees WHERE EmployeeID=" + id);
            while (rs.next()) {
                return new Employee(rs.getString("EmployeeID"),
                        rs.getObject("HireDate", LocalDate.class), rs.getString("FullName"),
                        rs.getObject("BirthDate", LocalDate.class),
                        Gender.valueOf(rs.getString("Gender")),
                        rs.getString("IdentityCard"), rs.getString("Address"),
                        rs.getString("Phone"), rs.getString("AccountID"));
            }
            s.close();
            c.close();
        }
        return null;
    }

    public Employee getEmployeeByAccountID(String id) {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees WHERE AccountID=" + id);
            while (rs.next()) {
                return new Employee(rs.getString("EmployeeID"),
                        rs.getObject("HireDate", LocalDate.class), rs.getString("FullName"),
                        rs.getObject("BirthDate", LocalDate.class),
                        Gender.valueOf(rs.getString("Gender")),
                        rs.getString("IdentityCard"), rs.getString("Address"),
                        rs.getString("Phone"), rs.getString("AccountID"));
            }
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
            ps.close();
            c.close();
        }
        return false;
    }
}
