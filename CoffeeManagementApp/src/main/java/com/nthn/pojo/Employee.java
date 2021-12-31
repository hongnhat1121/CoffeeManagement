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
    private State state;

    public Employee() {
    }

    public Employee(String employeeID, Date hireDate, State state) {
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.state = state;
    }

    public Employee(String employeeID, Date hireDate, State state,
            String accountID, String lastName, String firstName, Gender gender,
            String address, String phone) {
        super(accountID, lastName, firstName, gender, address, phone);
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.state = state;
    }

    public Employee(String employeeID, Date hireDate, State state,
            String accountID, String lastName, String firstName, Gender gender,
            String address, String phone, String username, String password) {
        super(accountID, lastName, firstName, gender, address, phone, username,
                password);
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.state = state;
    }

    public Employee(String employeeID, Date hireDate, State state,
            String accountID, String lastName, String firstName, Gender gender,
            String address, String phone, String username, String password,
            Active active, Role role) {
        super(accountID, lastName, firstName, gender, address, phone, username,
                password, active, role);
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.state = state;
    }

    @Override
    public void viewDetail() throws SQLException {
        super.viewDetail();
        System.out.println("Ngày vào làm: " + Utils.DATEFORMAT.format(getHireDate()));
        System.out.println("Bộ phận: " + this.state);
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", super.toString(),
                Utils.DATEFORMAT.format(getHireDate()), this.getState());
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

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(State state) {
        this.state = state;
    }

}
