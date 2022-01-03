/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.configs.Utils;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class Employee extends User {

    private String employeeID;
    private Date hireDate;

    public Employee() {
    }

    public Employee(String employeeID, Date hireDate) {
        this.employeeID = employeeID;
        this.hireDate = hireDate;
    }

    public Employee(String employeeID, Date hireDate, String fullName, Date birthDate, Gender gender, String identityCard, String address, String phone, String accountID) {
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
     * @return the hireDate
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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

}
