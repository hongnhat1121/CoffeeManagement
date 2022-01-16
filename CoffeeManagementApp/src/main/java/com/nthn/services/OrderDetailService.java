/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.OrderDetail;

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
public class OrderDetailService {

    /**
     * @param orderDetail
     * @return
     */
    public boolean addOrderDetail(OrderDetail orderDetail) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "orderdetails(OrderID, ProductID, Quantity, UnitPrice, Note) "
                    + "VALUES(?,?,?,?,?)");

            ps.setString(1, orderDetail.getOrderID());
            ps.setString(2, orderDetail.getProductID());
            ps.setInt(3, orderDetail.getQuantity());
            ps.setLong(4, orderDetail.getUnitPrice());
            ps.setString(5, orderDetail.getNote());

            ps.executeUpdate();
            connection.commit();

            ps.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * @return
     */
    public List<OrderDetail> getOrderDetails() {
        try (Connection c = JdbcUtils.getConnection()) {
            List<OrderDetail> orderDetails = new ArrayList<>();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT OrderID, ProductID, products.ProductName,"
                    + " Quantity, UnitPrice, Note FROM orderdetails, products "
                    + "WHERE orderdetails.ProductID=products.ProductID");
            while (rs.next()) {
                OrderDetail od = new OrderDetail(rs.getString("OrderID"),
                        rs.getString("ProductID"), rs.getString("ProductName"), rs.getInt("Quantity"),
                        rs.getLong("UnitPrice"), rs.getString("Note"));
                orderDetails.add(od);
            }
            s.close();
            return orderDetails;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * @param orderID
     * @return
     */

    public List<OrderDetail> getOrderDetailsByOrderID(String orderID) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {

            PreparedStatement ps = c.prepareStatement("SELECT products.ProductID, products.ProductName, Quantity, products.UnitPrice, Note " +
                    "FROM orderdetails JOIN products ON orderdetails.ProductID=products.ProductID " +
                    "WHERE OrderID=? GROUP BY products.ProductName;");
            ps.setString(1, orderID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail(orderID,
                        rs.getString("ProductID"), rs.getString("ProductName"),
                        rs.getInt("Quantity"), rs.getLong("UnitPrice"), rs.getString("Note"));
                orderDetails.add(od);
            }


        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetails;
    }

    /**
     * @param productID
     * @return
     */
    public List<OrderDetail> getOrderDetailsByProductID(String productID) {
        try (Connection c = JdbcUtils.getConnection()) {
            List<OrderDetail> orderDetails = new ArrayList<>();

            PreparedStatement ps = c.prepareStatement("SELECT OrderID, "
                    + " products.ProductName, Quantity, UnitPrice, Note "
                    + "FROM orderdetails, products "
                    + "WHERE orderdetails.ProductID=products.ProductID AND ProductID=?");
            ps.setString(1, productID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail(rs.getString("OrderID"),
                        productID, rs.getString("ProductName"), rs.getInt("Quantity"),
                        rs.getLong("UnitPrice"), rs.getString("Note"));
                orderDetails.add(od);
            }

            return orderDetails;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
