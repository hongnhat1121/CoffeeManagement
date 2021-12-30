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
        try (Connection conn = JdbcUtils.getConnection()){
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
    
    public List<Product> getProductsByName(String productName) throws SQLException {
        List<Product> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()){
            String sql = "SELECT * FROM products";
            if(productName != null && !productName.isEmpty())
                sql += " WHERE ProductName like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            if(productName != null && !productName.isEmpty())
                stm.setString(1, productName);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), 
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                results.add(p);
            }
        }
        return results;
    }
    
     public List<Product> getProductsByUnitPrice(Long productPrice) throws SQLException {
        List<Product> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()){
            String sql = "SELECT * FROM products";
            if(productPrice != null)
                sql += "WHERE UnitPrice like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            if(productPrice != null)
                stm.setLong(1, productPrice);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductId"), 
                        rs.getString("ProductName"), rs.getLong("UnitPrice"), rs.getInt("CategoryId"));
                results.add(p);
            }
        }
        return results;
    }
     
    public List<String> getColumnsName() throws SQLException{
        List<String> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT COLUMN_NAME\n" +
                                            "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                                            "WHERE TABLE_SCHEMA = 'coffeemanagementdb' AND TABLE_NAME = 'products'");
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                if(!columnName.contains("ID"))
                    results.add(columnName);
            }
        }
        return results;
    }
}
