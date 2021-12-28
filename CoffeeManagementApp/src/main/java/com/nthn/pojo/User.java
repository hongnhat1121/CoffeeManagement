/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public class User extends Account {

    private Account account;
    private String lastName;
    private String firstName;
    private Gender gender;
    private String address;
    private String phone;

    public User() {
    }

    public User(Account account, String lastName, String firstName, Gender gender, String address, String phone) {
        this.account = account;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public void display() {
        System.out.println("Họ và tên: " + lastName + " " + firstName);
        System.out.println("Giới tính: " + gender.toString());
        System.out.println("Địa chỉ: " + address);
        System.out.println("Số điện thoại: " + phone);
    }

    @Override
    public String toString() {
        return String.format("", lastName);
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
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

}
