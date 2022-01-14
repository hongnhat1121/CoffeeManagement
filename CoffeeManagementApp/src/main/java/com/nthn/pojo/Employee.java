/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.configs.Utils;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author HONGNHAT
 */
public class Employee {

    private String employeeID;
    private String fullName;
    private Gender gender;
    private LocalDate birthDate;
    private String identityCard;
    private String phone;
    private String address;
    private LocalDate hireDate;
    private Account account;

    public Employee() {
    }

    public Employee(String employeeID, String fullName, Gender gender, LocalDate birthDate, String identityCard, String phone, String address, LocalDate hireDate, Account account) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.identityCard = identityCard;
        this.phone = phone;
        this.address = address;
        this.hireDate = hireDate;
        this.account = account;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    /**
     * @return the employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the hireDate
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
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

}
