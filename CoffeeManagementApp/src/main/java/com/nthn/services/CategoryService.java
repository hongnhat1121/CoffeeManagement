/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Category;
import com.nthn.pojo.Role;
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
public class CategoryService {

    public List<Category> getCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();

        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM categories");

            while (rs.next()) {
                Category category = new Category(rs.getInt("CategoryID"), rs.getString("CategoryName"));
                categories.add(category);
            }
        }

        return categories;
    }
    
   public void addCategory(Category category) {

        try {
            Connection connection = JdbcUtils.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories(CategoryID, CategoryName) VALUES(?,?)");
            preparedStatement.setInt(1, category.getCategoryID());
            preparedStatement.setString(2, category.getCategoryName());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
