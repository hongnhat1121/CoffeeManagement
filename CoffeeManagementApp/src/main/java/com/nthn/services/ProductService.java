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
                Product p = new Product(rs.getInt("ProductId"),
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
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                return p;
            }
        }
        return null;
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
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                results.add(p);
            }
        }
        return results;
    }
<<<<<<< HEAD

    public List<Product> getProductsByUnitPrice(String productPrice) throws SQLException {
=======
    
     public List<Product> getProductsByUnitPrice(Long productPrice) throws SQLException {
>>>>>>> 8ed4e65eac4271ded17278f6363a76ebadd0c8ed
        List<Product> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()) {
            String sql = "SELECT * FROM products";
<<<<<<< HEAD
            if (productPrice != null && !productPrice.isEmpty()) {
=======
            if(productPrice != null)
>>>>>>> 8ed4e65eac4271ded17278f6363a76ebadd0c8ed
                sql += "WHERE UnitPrice like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareStatement(sql);
<<<<<<< HEAD
            if (productPrice != null && !productPrice.isEmpty()) {
                stm.setString(1, productPrice);
            }
=======
            if(productPrice != null)
                stm.setLong(1, productPrice);
>>>>>>> 8ed4e65eac4271ded17278f6363a76ebadd0c8ed
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"),
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                results.add(p);
            }
        }
        return results;
    }
}
