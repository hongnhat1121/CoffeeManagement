/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Order;
import com.nthn.pojo.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HONGNHAT
 */
public class OrderService {

    /**
     *
     * @return @throws SQLException
     */
    public List<Order> getOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();

        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                Order o = new Order(rs.getString("OrderID"), rs.getDate("OrderDate"),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        rs.getString("TableID"), rs.getInt("Payment"));
                orders.add(o);
            }
        }
        return orders;
    }

    /**
     *
     * @param order
     */
    public void addOrder(Order order) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO orders(OrderID, OrderDate, Total, Payment, "
                    + "TableID, EmployeeID) VALUES(?,?,?,?,?,?)");

            preparedStatement.setString(1, order.getOrderID());
            preparedStatement.setDate(2, order.getOrderDate());
            preparedStatement.setBigDecimal(3, order.getTotal());
            preparedStatement.setInt(4, order.getPayment());
            preparedStatement.setString(5, order.getTableID());
            preparedStatement.setString(6, order.getEmployeeID());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Order getOrder(String id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM orders WHERE OrderID=" + id);
            while (rs.next()) {
                return new Order(rs.getString("OrderID"), rs.getDate("OrderDate"),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        rs.getString("TableID"), rs.getInt("Payment"));
            }
        }
        return null;
    }
}
