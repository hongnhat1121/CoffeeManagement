/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Category;
import com.nthn.pojo.Product;
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

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO tables(TableID, TableName, Capacity, Status) "
                    + "VALUES(?, ?, ?, ?)");

            preparedStatement.setString(1, table.getTableID());
            preparedStatement.setString(2, table.getTableName());
            preparedStatement.setInt(3, table.getCapacity());
            preparedStatement.setString(4, table.getStatus().name());

            preparedStatement.executeUpdate();

            connection.commit();

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TableService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Table getTable(String id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT TableName FROM tables WHERE TableID=" + id);
            while (rs.next()) {
                return new Table(rs.getString("TableID"),
                        rs.getString("TableName"), rs.getInt("Capacity"),
                        Status.getByContent(rs.getString("Status")));
            }
        }
        return null;
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
            
            if (Status1 != null && !Status1.isEmpty())
                sql += " Capacity like concat('%', ?, '%')";
          
            if(Capacity != null && !Capacity.isEmpty()) {
                if(Status1 != null && !Status1.isEmpty())
                    sql += " AND";
                sql += " Status like concat('%', ?, '%')";
            }
            
            System.out.print(sql);
            PreparedStatement stm = conn.prepareStatement(sql);
            
            if (Status1 != null && !Status1.isEmpty())
                stm.setString(1, Capacity);
          
            if(Capacity != null && !Capacity.isEmpty()) {
                if(Status1 != null && !Status1.isEmpty())
                    stm.setString(2, statusContent);
                else
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

}
