/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.services;

import com.nthn.configs.JdbcUtils;
import com.nthn.pojo.Account;
import com.nthn.pojo.Active;
import com.nthn.pojo.Employee;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HONGNHAT
 */
public class AccountService {

    /**
     *
     * @return @throws SQLException
     */
    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        try (Connection c = JdbcUtils.getConnection()) {
            try (Statement s = c.createStatement()) {
                ResultSet rs = s.executeQuery("SELECT * FROM accounts");
                while (rs.next()) {
                    Account a = new Account(rs.getString("AccountID"),
                            rs.getString("Username"), rs.getString("Password"),
                            Active.valueOf(rs.getString("Active")),
                            Role.valueOf(rs.getString("Role")));
                    accounts.add(a);
                }
            }
            c.close();
        }
        return accounts;
    }

    /**
     *
     * @param account
     */
    public void addAccount(Account account) {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO "
                    + "accounts(AccountID, Username, Password, Active, Role) "
                    + "VALUES(?,?,?,?,?)");

            ps.setString(1, account.getAccountID());
            ps.setString(2, account.getUsername());
            ps.setString(3, DigestUtils.sha256Hex(account.getPassword()));
            ps.setString(4, account.getActive().name());
            ps.setString(5, account.getRole().name());

            ps.executeUpdate();

            connection.commit();

            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAccount(Account account) throws SQLException {
        try ( Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO accounts(AccountID, Username, Password, Active, Role) "
                    + "VALUES(?,?,?,?,?)");
            ps1.setString(1, account.getAccountID());
            ps1.setString(2, account.getUsername());
            ps1.setString(3, DigestUtils.sha256Hex(account.getPassword()));
            ps1.setString(4, account.getActive().name());
            ps1.setString(5, account.getRole().name());

            ps1.executeUpdate();

            connection.commit();
            ps1.close();
        }
    }
    
    public void deleteAccount(String account) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM accounts" +
                    "WHERE AccountID = ?")) {
                preparedStatement.setString(1, account);
                
                preparedStatement.executeUpdate();
                
                connection.commit();
            }
        }
    }
    

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Account getAccountByID(String id) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM accounts WHERE AccountID=" + id);
            while (rs.next()) {
                return new Account(rs.getString("AccountID"),
                        rs.getString("Username"), rs.getString("Password"),
                        Active.valueOf(rs.getString("Active")),
                        Role.valueOf(rs.getString("Role")));
            }
        }
        return null;
    }

    /**
     *
     * @param text
     * @return
     * @throws SQLException
     */
    public Account getAccountByUsername(String text) throws SQLException {
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM accounts WHERE Username = ?");
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString("AccountID"),
                        rs.getString("Username"), rs.getString("Password"),
                        Active.valueOf(rs.getString("Active")),
                        Role.valueOf(rs.getString("Role")));
            }
            ps.close();
            c.close();
        }
        return null;
    }

    /**
     * Kiểm tra username, password khớp với dữ liệu chưa?
     *
     * @param username
     * @param password
     * @return
     */
    public boolean checkAccount(String username, String password) {
        try (Connection c = JdbcUtils.getConnection()) {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM accounts WHERE Username = ? AND Password = ?");
            ps.setString(1, username);
            ps.setString(2, DigestUtils.sha256Hex(password));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            ps.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
