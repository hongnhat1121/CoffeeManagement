/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author HONGNHAT
 */
public abstract class User {

    private String fullName;
    private LocalDate birthDate;
    private Gender gender;
    private String identityCard;
    private String address;
    private String phone;
    private String accountID;

    public User() {
    }

    public User(String fullName, LocalDate birthDate, Gender gender, String identityCard, String address, String phone, String accountID) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.identityCard = identityCard;
        this.address = address;
        this.phone = phone;
        this.accountID = accountID;
    }

    public void viewDetail() throws SQLException {
        System.out.println("Họ và tên: " + getFullName());
        System.out.println("Giới tính: " + getGender());
        System.out.println("Địa chỉ: " + getAddress());
        System.out.println("Số điện thoại: " + getPhone());
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s  - %s", this.getFullName(), this.getGender(), this.getPhone(), this.getAddress());
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
     * @return the identityCard
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * @param identityCard the identityCard to set
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
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
