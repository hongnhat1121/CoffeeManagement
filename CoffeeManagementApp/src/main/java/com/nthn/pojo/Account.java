/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.services.ActiveService;
import com.nthn.services.RoleService;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils; //Apache Commons Codecs - SHA256

/**
 *
 * @author HONGNHAT
 */
public class Account {

    private String accountID;
    private String username;
    private String password;
    private int activeID;
    private int roleID;

    public Account() {
    }

    public Account(String accountID, String username, String password, int roleID) {
        this.accountID = accountID;
        this.username = username;
        this.password = DigestUtils.sha256Hex(password);
        this.activeID = Active.AVAILABLE.getActiveId();
        this.roleID = roleID;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = DigestUtils.sha256Hex(password);
    }

    public void changePassword(String text) {
        this.setPassword(DigestUtils.sha256Hex(text));
    }

    public void changeRole(int role) {
        this.setRoleID(role);
    }

    public void display() throws SQLException {
        System.out.println("Tên đăng nhập: " + this.getUsername());
        System.out.println("Hoạt động: " + new ActiveService().getActive(getActiveID()));
        System.out.println("Phân quyền: " + Role.getRoleByID(roleID));
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }

    /**
     * @return the activeID
     */
    public int getActiveID() {
        return activeID;
    }

    /**
     * @param activeID the activeID to set
     */
    public void setActiveID(int activeID) {
        this.activeID = activeID;
    }

    /**
     * @return the roleID
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * @param roleID the roleID to set
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    /**
     * @return the accountID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
