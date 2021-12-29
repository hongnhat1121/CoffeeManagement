/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.configs.Utils;
import com.nthn.services.ActiveService;
import com.nthn.services.RoleService;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils; //Apache Commons Codecs - SHA256

/**
 *
 * @author HONGNHAT
 */
public class Account {

    private int accountID;
    private String username;
    private String password;
    private int activeID;
    private int roleID;

    public Account() {
    }

    public Account(int accountID, String username, String password, int activeID, int roleID) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.activeID = activeID;
        this.roleID = roleID;
    }

    public void inputUsername() {
        System.out.println("com.nthn.pojo.Account.inputUsername()");
        this.setUsername(Utils.SCANNER.nextLine());
    }

    public void inputPassword() throws NoSuchAlgorithmException {
        System.out.println("com.nthn.pojo.Account.inputPassword()");
        this.setPassword(DigestUtils.sha256Hex(Utils.SCANNER.nextLine())); //Apache Commons Codecs - SHA256
    }

    public void changePassword(String text) {
        this.setPassword(text);
    }

    public void changeRole(int role) {
        this.setRoleID(role);
    }

    public void display() throws SQLException {
        System.out.println("Tên đăng nhập: " + this.getUsername());
        System.out.println("Mật khẩu: " + this.getPassword());
        System.out.println("Hoạt động: " + new ActiveService().getActive(getActiveID()));
        System.out.println("Phân quyền: " + new RoleService().getRole(getRoleID()));
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
        this.password = password;
    }

    /**
     * @return the accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the roleID
     */
    public int getRole() {
        return getRoleID();
    }

    /**
     * @param role the roleID to set
     */
    public void setRole(int role) {
        this.setRoleID(role);
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
}
