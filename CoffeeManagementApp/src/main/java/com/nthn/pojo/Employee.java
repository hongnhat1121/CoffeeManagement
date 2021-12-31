/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.configs.Utils;
import com.nthn.services.StateService;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class Employee extends User {

    private int employeeID;
    private Date hireDate;
    private int stateID;

    public Employee() {
    }

    public Employee(int employeeID, Date hireDate, int stateID) {
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.stateID = stateID;
    }

    public Employee(int employeeID, Date hireDate, int stateID, int accountID, String lastName, String firstName, int genderID, String address, String phone) {
        super(accountID, lastName, firstName, genderID, address, phone);
        this.employeeID = employeeID;
        this.hireDate = hireDate;
        this.stateID = stateID;
    }

    @Override
    public void viewDetail() throws SQLException {
        super.viewDetail();
        System.out.println("Ngày vào làm: " + Utils.DATEFORMAT.format(getHireDate()));
        System.out.println("Bộ phận: " + new StateService().getState(getStateID()));
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
     * @return the stateID
     */
    public int getStateID() {
        return stateID;
    }

    /**
     * @param stateID the stateID to set
     */
    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

}
