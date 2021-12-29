/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.configs.Utils;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils; //Apache Commons Codecs - SHA256

/**
 *
 * @author HONGNHAT
 */
public class Account {

    private int accountID;
    private String username;
    private String password;
    private Active active;
    private Role role;

    public Account() {
    }

    public Account(String username, String password, Active active, Role role) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
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

    public void changeRole(Role role) {
        this.role = role;
    }

    public void display() {
        System.out.println("Tên đăng nhập: " + this.username);
        System.out.println("Mật khẩu: " + this.password);
        System.out.println("Hoạt động: " + this.active.getAcitveName());
        System.out.println("Phân quyền: " + this.role.getRoleName());
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
