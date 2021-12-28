/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class ActiveService {
    public List<String> getActives() throws SQLException {
        List<String> roles = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM roles");
            while (rs.next()) {
                roles.add(rs.getString("roleName"));
            }
        }
        return roles;
    }

    public void addActive(Active role) {

        try {
            Connection connection = JdbcUtils.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO roles(RoleID, RoleName) VALUES(?,?)");
            preparedStatement.setInt(1, role.getRoleID());
            preparedStatement.setString(2, role.getRoleName());

            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
