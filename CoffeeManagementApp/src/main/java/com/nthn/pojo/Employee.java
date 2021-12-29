/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import java.sql.Date;

/**
 *
 * @author HONGNHAT
 */
public class Employee extends User {

    private int employeeID;
    private Date hireDate;
    private State state;

    public Employee() {
    }

    public Employee(int employeeID, Date hireDate, State state) {
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.state = state;
    }

    public Employee(int employeeID, Date hireDate, State state, Account account, String lastName, String firstName, Gender gender, String address, String phone) {
        super(account, lastName, firstName, gender, address, phone);
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.state = state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    @Override
    public void viewDetail() {
        super.viewDetail();
        System.out.println("Ngày vào làm: " + this.hireDate.toString());
        System.out.println("Bộ phận: " + this.state.getStateName());
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
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
