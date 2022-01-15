/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HONGNHAT
 */
public class OrderService {

    /**
     * @param order
     * @return
     */
    public boolean addOrder(Order order) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "orders(OrderID, OrderDate, Total, Payment, "
                    + "TableID, EmployeeID) VALUES(?,?,?,?,?,?)");

            ps.setString(1, order.getOrderID());
            ps.setObject(2, order.getOrderDate());
            ps.setBigDecimal(3, order.getTotal());
            ps.setInt(4, order.getPayment());
            ps.setString(5, order.getTableID());
            ps.setString(6, order.getEmployeeID());

            ps.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * @return
     */
    public List<Order> getOrders() {
        try (Connection c = JdbcUtils.getConnection()) {
            List<Order> orders = new ArrayList<>();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM orders");
            while (rs.next()) {
                Order o = new Order(rs.getString("OrderID"), rs.getObject("OrderDate", LocalDate.class),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        rs.getString("TableID"), rs.getInt("Payment"));
                orders.add(o);
            }
            s.close();
            return orders;
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param tableID
     * @return
     */
    public List<Order> getOrdersByTableID(String tableID) {
        try (Connection c = JdbcUtils.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM orders WHERE TableID=?");
            ps.setString(1, tableID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order(rs.getString("OrderID"), rs.getObject("OrderDate", LocalDate.class),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        tableID, rs.getInt("Payment"));
                orders.add(o);
            }
            return orders;
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public Order getOrderByID(String id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM orders WHERE OrderID=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Order(rs.getString("OrderID"), rs.getObject("OrderDate", LocalDate.class),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        rs.getString("TableID"), rs.getInt("Payment"));
            }
        }
        return null;
    }

    //Lấy danh sách hóa đơn chưa thanh toán
    public List<Order> getOrdersNotPay() {
        List<Order> orders = new ArrayList<>();

        try (Connection c = JdbcUtils.getConnection()) {

            PreparedStatement ps = c.prepareStatement("SELECT * FROM orders WHERE Payment=?");
            ps.setInt(1, 0);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order(rs.getString("OrderID"), rs.getObject("OrderDate", LocalDate.class),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        rs.getString("TableID"), 0);
                orders.add(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public List<Order> getOrders(String tableName) {
        List<Order> orders=new ArrayList<>();
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM orders JOIN tables"
                    + " ON orders.TableID=tables.TableID WHERE tables.TableName like concat('%', ?, '%')");
            statement.setString(1, tableName);

            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Order order=new Order(rs.getString("OrderID"), rs.getObject("OrderDate", LocalDate.class),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        rs.getString("TableID"), rs.getInt("Payment"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrders(LocalDate localDate) {
        List<Order> orders = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM orders WHERE OrderDate=?");
            ps.setObject(1, localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order= new Order(rs.getString("OrderID"), rs.getObject("OrderDate", LocalDate.class),
                        rs.getBigDecimal("Total"), rs.getString("EmployeeID"),
                        rs.getString("TableID"), rs.getInt("Payment"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void updateOrder(Order order) {
        try (Connection c = JdbcUtils.getConnection()) {
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement("UPDATE orders SET Payment=? WHERE OrderID=?");
            ps.setInt(1, 1);
            ps.setString(2, order.getOrderID());

            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
