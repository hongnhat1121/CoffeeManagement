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
public abstract class User extends Account {

    private String accountID;
    private String lastName;
    private String firstName;
    private Gender gender;
    private String address;
    private String phone;

    public User() {
    }

    public User(String accountID, String lastName, String firstName,
            Gender gender, String address, String phone) {
        this.accountID = accountID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public User(String accountID, String lastName, String firstName,
            Gender gender, String address, String phone, String username,
            String password) {
        super(username, password);
        this.accountID = accountID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public User(String accountID, String lastName, String firstName,
            Gender gender, String address, String phone, String username,
            String password, Active active, Role role) {
        super(accountID, username, password, active, role);
        this.accountID = accountID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public void viewDetail() throws SQLException {
        System.out.println("Họ và tên: " + getLastName() + " " + getFirstName());
        System.out.println("Giới tính: " + getGender());
        System.out.println("Địa chỉ: " + getAddress());
        System.out.println("Số điện thoại: " + getPhone());
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s  - %s", this.getLastName(), this.getFirstName(), this.getGender(), this.getPhone());
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the accountID
     */
    @Override
    public String getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    @Override
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
