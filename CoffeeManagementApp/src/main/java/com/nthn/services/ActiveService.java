/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Active;
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
public class ActiveService {

    public List<String> getActives() throws SQLException {
        List<String> actives = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM actives");
            while (rs.next()) {
                actives.add(rs.getString("activeName"));
            }
        }
        return actives;
    }

    public void addActive(Active active) {
        try {
            Connection connection = JdbcUtils.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO actives(ActiveID, ActiveName) VALUES(?,?)");
            preparedStatement.setInt(1, active.getActiveId());
            preparedStatement.setString(2, active.getAcitveName());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ActiveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getActive(int activeID) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT ActiveName FROM actives WHERE ActiveID=" + activeID);
            while (rs.next()) {
                return rs.getString("ActiveName");
            }
        }
        return null;
    }
}
