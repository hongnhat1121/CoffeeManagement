/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Table;
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
public class TableService {

    public List<Table> getTables() throws SQLException {
        List<Table> tables = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tables");
            while (rs.next()) {
                tables.add(new Table(rs.getInt("TableID"), rs.getInt("Capacity"), rs.getInt("StatusID")));
            }
        }
        return tables;
    }

    public void addTable(Table table) {

        try {
            Connection connection = JdbcUtils.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO tables(TableID, Capacity, StatusID) "
                    + "VALUES(?, ?, ?)");
            preparedStatement.setInt(1, table.getTableID());
            preparedStatement.setInt(2, table.getCapacity());
            preparedStatement.setInt(3, table.getStatusID());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TableService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Table getTable(int id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT TableName FROM tables WHERE TableID=" + id);
            while (rs.next()) {
                return new Table(rs.getInt("TableID"),
                        rs.getInt("Capacity"), rs.getInt("StatusID"));
            }
        }
        return null;
    }
}