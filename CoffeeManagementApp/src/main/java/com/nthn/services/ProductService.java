/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nthn.services;

import com.nthn.pojo.Product;
import com.nthn.configs.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ProductService {

    public List<Product> getProducts() throws SQLException {
        List<Product> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM products");

            while (rs.next()) {
                Product p = new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                results.add(p);
            }
        }
        return results;
    }

    public Product getProduct(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConnection()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM products WHERE ProductID=" + id);

            while (rs.next()) {
                Product p = new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                return p;
            }
        }
        return null;
    }

    public void addProduct(Product p) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO products(ProductID, ProductName, UnitPrice, CategoryID) "
                    + "VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, p.getProductID());
            preparedStatement.setString(2, p.getProductName());
            preparedStatement.setLong(3, p.getUnitPrice());
            preparedStatement.setInt(4, p.getCategoryId());

            preparedStatement.executeUpdate();

            connection.commit();
        }
    }

    public List<Product> getProductsByName(String productName) throws SQLException {
        List<Product> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()) {
            String sql = "SELECT * FROM products";
            if (productName != null && !productName.isEmpty()) {
                sql += "WHERE ProductName like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareStatement(sql);
            if (productName != null && !productName.isEmpty()) {
                stm.setString(1, productName);
            }
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getString("ProductID"),
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                results.add(p);
            }
        }
        return results;
    }
//
//    public List<Product> getProductsByUnitPrice(Long productPrice) throws SQLException {
//
//        List<Product> results = new ArrayList<>();
//        try (Connection conn = JdbcUtils.getConnection()) {
//            String sql = "SELECT * FROM products";
//            if (productPrice != null && !productPrice.isEmpty()) {
//
//                if (productPrice != null) {
//                    sql += "WHERE UnitPrice like concat('%', ?, '%')";
//                }
//            }
//
//            PreparedStatement stm = conn.prepareStatement(sql);
//            if (productPrice != null && !productPrice.isEmpty()) {
//                stm.setString(1, productPrice);
//            }
//            if (productPrice != null) {
//                stm.setLong(1, productPrice);
//            }
//            ResultSet rs = stm.executeQuery();
//
//            while (rs.next()) {
//                Product p = new Product(rs.getInt("ProductId"),
//                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
//                results.add(p);
//            }
//        }
//        return results;
//    }
}
