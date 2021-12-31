/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Employee;
import com.nthn.pojo.Gender;
import com.nthn.pojo.State;
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

    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                Employee e = new Employee(rs.getString("EmployeeID"),
                        rs.getDate("HireDate"), State.valueOf(rs.getString("State")),
                        rs.getString("AccountID"), rs.getString("LastName"),
                        rs.getString("FirstName"), Gender.valueOf("Gender"),
                        rs.getString("Address"), rs.getString("Phone"));
                employees.add(e);
            }
        }
        return employees;
    }

    public void addEmployee(Employee e) {

        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employees(EmployeeID, LastName, FirstName, "
                    + "HireDate, State, AccountID, Gender, Address, Phone) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, e.getEmployeeID());
            preparedStatement.setString(2, e.getLastName());
            preparedStatement.setString(3, e.getFirstName());
            preparedStatement.setDate(4, e.getHireDate());
            preparedStatement.setString(5, e.getState().name());
            preparedStatement.setString(6, e.getAccountID());
            preparedStatement.setString(7, e.getGender().name());
            preparedStatement.setString(8, e.getAddress());
            preparedStatement.setString(9, e.getPhone());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee getEmployee(String id) throws SQLException {

        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM employees WHERE EmployeeID=" + id);
            while (rs.next()) {
                return new Employee(rs.getString("EmployeeID"),
                        rs.getDate("HireDate"), State.valueOf(rs.getString("State")),
                        rs.getString("AccountID"), rs.getString("LastName"),
                        rs.getString("FirstName"), Gender.valueOf("Gender"),
                        rs.getString("Address"), rs.getString("Phone"));
            }
        }
        return null;
    }
}
