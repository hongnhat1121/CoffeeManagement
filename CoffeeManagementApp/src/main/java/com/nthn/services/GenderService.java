/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
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
public class GenderService {

    public List<String> getGenders() throws SQLException {
        List<String> genders = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM genders");
            while (rs.next()) {
                genders.add(rs.getString("genderName"));
            }
        }
        return genders;
    }

    public void addGender(Gender gender) {
        try {
            Connection connection = JdbcUtils.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO genders(GenderID, GenderName) VALUES(?,?)");
            preparedStatement.setInt(1, gender.getGenderID());
            preparedStatement.setString(2, gender.getGenderName());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GenderService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getGender(int id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT GenderName FROM genders WHERE GenderID=" + id);
            while (rs.next()) {
                return rs.getString("genderName");
            }
        }
        return null;
    }
}
