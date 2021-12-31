/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.services.GenderService;
import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public abstract class User extends Account {

    private String accountID;
    private String lastName;
    private String firstName;
    private int genderID;
    private String address;
    private String phone;

    public User() {
    }

    public User(String accountID, String lastName, String firstName, int genderID, String address, String phone) {
        this.accountID = accountID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.genderID = genderID;
        this.address = address;
        this.phone = phone;
    }

    public void edit(String text) {

    }

    public void edit(int genderId) {
        this.setGenderID(genderId);
    }

    public void viewDetail() throws SQLException {
        System.out.println("Họ và tên: " + getLastName() + " " + getFirstName());
        System.out.println("Giới tính: " + new GenderService().getGender(getGenderID()));
        System.out.println("Địa chỉ: " + getAddress());
        System.out.println("Số điện thoại: " + getPhone());
    }

    @Override
    public String toString() {
        return String.format("", getLastName());
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
     * @return the genderID
     */
    public int getGenderID() {
        return genderID;
    }

    /**
     * @param genderID the genderID to set
     */
    public void setGenderID(int genderID) {
        this.genderID = genderID;
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

}
