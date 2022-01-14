/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Status;
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

    public void addTable(Table table) {

        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "tables(TableID, TableName, Capacity, Status) "
                    + "VALUES(?, ?, ?, ?)");

            ps.setString(1, table.getTableID());
            ps.setString(2, table.getTableName());
            ps.setInt(3, table.getCapacity());
            ps.setString(4, table.getStatus().name());

            ps.executeUpdate();

            connection.commit();

            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Table getTable(String id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {

            PreparedStatement ps = c.prepareStatement("SELECT * FROM tables WHERE TableID = ?");
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Table(rs.getString("TableID"),
                        rs.getString("TableName"), rs.getInt("Capacity"),
                        Status.valueOf(rs.getString("Status")));
            }
        }
        return null;
    }

    public void updateTable(Table t) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE tables "
                    + "SET TableName = ?, Capacity = ?, Status = ?"
                    + "WHERE TableID = ?")) {
                preparedStatement.setString(4, t.getTableID());
                preparedStatement.setString(1, t.getTableName());
                preparedStatement.setInt(2, t.getCapacity());
                preparedStatement.setString(3, t.getStatus().name());

                preparedStatement.executeUpdate();

                connection.commit();
            }
        }
    }

    public void deleteTable(String t) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM tables "
                    + "WHERE TableID = ?")) {
                preparedStatement.setString(1, t);

                preparedStatement.executeUpdate();

                connection.commit();
            }
        }
    }

    public List<Table> getTablesByName(String TableName) throws SQLException {
        List<Table> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()) {
            String sql = "SELECT * FROM tables";
            if (TableName != null && !TableName.isEmpty()) {
                sql += " WHERE TableName like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareStatement(sql);
            if (TableName != null && !TableName.isEmpty()) {
                stm.setString(1, TableName);
            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Table p = new Table(rs.getString("TableID"),
                        rs.getString("TableName"), rs.getInt("Capacity"),
                        Status.getByContent(rs.getString("Status")));
                results.add(p);
            }

        }
        return results;
    }

    public List<Table> getTablesByCapacity(String Capacity) throws SQLException {
        List<Table> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()) {
            String sql = "SELECT * FROM tables";
            if (Capacity != null && !Capacity.isEmpty()) {
                sql += " WHERE Capacity like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareStatement(sql);
            if (Capacity != null && !Capacity.isEmpty()) {
                stm.setString(1, Capacity);
            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Table p = new Table(rs.getString("TableID"),
                        rs.getString("TableName"), rs.getInt("Capacity"),
                        Status.getByContent(rs.getString("Status")));
                results.add(p);
            }
        }
        return results;
    }

    public List<Table> getTablesByStatus(String Status1) throws SQLException {
        List<Table> results = new ArrayList<>();
        String statusContent = Status.getByContent(Status1).name();

        try (Connection conn = JdbcUtils.getConnection()) {
            String sql = "SELECT * FROM tables";
            if (Status1 != null && !Status1.isEmpty()) {
                sql += " WHERE Status like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareStatement(sql);
            if (Status1 != null && !Status1.isEmpty()) {
                stm.setString(1, statusContent);
            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Table p = new Table(rs.getString("TableID"),
                        rs.getString("TableName"), rs.getInt("Capacity"),
                        Status.getByContent(rs.getString("Status")));
                results.add(p);
            }
        }
        return results;
    }

    public List<Table> getTablesByAll(String Capacity, String Status1) throws SQLException {
        List<Table> results = new ArrayList<>();
        String statusContent = Status.getByContent(Status1).name();

        try (Connection conn = JdbcUtils.getConnection()) {
            String sql = "SELECT * FROM tables WHERE";

            if (Capacity != null && !Capacity.isEmpty()) {
                sql += " Capacity like concat('%', ?, '%')";
            }

            if (Status1 != null && !Status1.isEmpty()) {
                if (Capacity != null && !Capacity.isEmpty()) {
                    sql += " AND";
                }
                sql += " Status like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareStatement(sql);

            if (Capacity != null && !Capacity.isEmpty()) {
                stm.setString(1, Capacity);
            }

            if (Status1 != null && !Status1.isEmpty()) {
                if (Capacity != null && !Capacity.isEmpty()) {
                    stm.setString(2, statusContent);
                } else {
                    stm.setString(1, statusContent);
                }
            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Table p = new Table(rs.getString("TableID"),
                        rs.getString("TableName"), rs.getInt("Capacity"),
                        Status.getByContent(rs.getString("Status")));
                results.add(p);
            }
        }
        return results;
    }

    public List<Table> getTables() throws SQLException {
        List<Table> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection()) {
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM tables");
            while (rs.next()) {
                Table p = new Table(rs.getString("TableID"),
                        rs.getString("TableName"), rs.getInt("Capacity"),
                        Status.getByContent(rs.getString("Status")));
                results.add(p);
            }
        }
        return results;
    }

}
