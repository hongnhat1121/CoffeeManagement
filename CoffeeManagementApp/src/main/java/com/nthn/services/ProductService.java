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
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM products WHERE ProductName = %(?)%");
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
}
