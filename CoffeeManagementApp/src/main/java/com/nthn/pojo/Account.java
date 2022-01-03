/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class Account {

    private String accountID;
    private String username;
    private String password;
    private Active active;
    private Role role;

    public Account() {
    }

    public Account(String accountID, String username, String password, Active active, Role role) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

//
//    public static String createPassword() {
//        char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?").toCharArray();
//        return RandomStringUtils.random(0, 6, 0, true, true, possibleCharacters);
//    }
    public void viewDetail() throws SQLException {
        System.out.println("Tên đăng nhập: " + this.getUsername());
        System.out.println("Hoạt động: " + this.getActive().getContent());
        System.out.println("Phân quyền: " + this.getRole().getContent());
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", this.username, this.active, this.role);
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
    public String getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the active
     */
    public Active getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Active active) {
        this.active = active;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
