/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HONGNHAT
 */
public class EmployeeService {

    public static List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                Employee e = new Employee(rs.getString("EmployeeID"),
                        rs.getDate("HireDate"), rs.getString("AccountID"),
                        rs.getString("FullName"), rs.getDate("BirthDate"),
                        Gender.valueOf("Gender"), rs.getString("IdentityCard"),
                        rs.getString("Address"), rs.getString("Phone"));
                employees.add(e);
            }
        }
        return employees;
    }

    public static void addEmployee(Employee e) {

        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employees(EmployeeID, FullName, BirthDate, "
                    + "Gender, IdentityCard, Phone, Address, "
                    + "HireDate, AccountID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, e.getEmployeeID());
            preparedStatement.setString(2, e.getFullName());
            preparedStatement.setDate(3, e.getBirthDate());
            preparedStatement.setString(4, e.getGender().name());
            preparedStatement.setString(5, e.getIdentityCard());
            preparedStatement.setString(6, e.getPhone());
            preparedStatement.setString(7, e.getAddress());
            preparedStatement.setDate(8, e.getHireDate());
            preparedStatement.setString(9, e.getAccountID());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Employee getEmployee(String id) throws SQLException {

        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees WHERE EmployeeID=" + id);
            while (rs.next()) {
                return new Employee(rs.getString("EmployeeID"),
                        rs.getDate("HireDate"), rs.getString("AccountID"),
                        rs.getString("FullName"), rs.getDate("BirthDate"),
                        Gender.valueOf("Gender"), rs.getString("IdentityCard"),
                        rs.getString("Address"), rs.getString("Phone"));
            }
        }
        return null;
    }
}
