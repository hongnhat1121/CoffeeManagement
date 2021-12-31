/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.services.StatusService;
import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class Table {

    private String tableID;
    private String tableName;
    private int capacity;
    private int statusID;

    public Table() {
    }

    public Table(String tableID, String tableName, int capacity, int statusID) {
        this.tableID = tableID;
        this.tableName = tableName;
        this.capacity = capacity;
        this.statusID = statusID;
    }

    public void edit(String text) {
        this.setTableName(text);
    }

    public void edit(int number) {
        this.setCapacity(number);
    }

    public void viewDetail() throws SQLException {
        System.out.print("Sức chứa: " + this.getCapacity());
        System.out.println("Trạng thái: " + Status.getStatusByID(statusID));
    }

    @Override
    public String toString() {
        return String.format("%s\t%d", this.getTableName(), this.getCapacity());
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the tableID
     */
    public String getTableID() {
        return tableID;
    }

    /**
     * @param tableID the tableID to set
     */
    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

}
