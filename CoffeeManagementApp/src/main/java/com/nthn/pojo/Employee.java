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
public class Employee extends User {

    private String employeeID;
    private LocalDate hireDate;

    public Employee() {
    }

    public Employee(String employeeID, LocalDate hireDate) {
        this.employeeID = employeeID;
        this.hireDate = hireDate;
    }

    public Employee(String employeeID, LocalDate hireDate, String fullName, LocalDate birthDate, Gender gender, String identityCard, String address, String phone, String accountID) {
        super(fullName, birthDate, gender, identityCard, address, phone, accountID);
        this.employeeID = employeeID;
        this.hireDate = hireDate;
    }

    @Override
    public void viewDetail() throws SQLException {
        super.viewDetail();
        System.out.println("Ngày vào làm: " + Utils.DATEFORMAT.format(getHireDate()));
    }

    @Override
    public String toString() {
        return String.format("%s - %s", super.toString(),
                Utils.DATEFORMAT.format(getHireDate()));
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

}
