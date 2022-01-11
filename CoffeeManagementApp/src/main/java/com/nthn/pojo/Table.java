/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class Table {

    private String tableID;
    private String tableName;
    private int capacity;
    private Status status;

    public Table() {
    }

    public Table(String tableID, String tableName, int capacity, Status status) {
        this.tableID = tableID;
        this.tableName = tableName;
        this.capacity = capacity;
        this.status = status;
    }

    public void edit(String text) {
        this.setTableName(text);
    }

    public void edit(int number) {
        this.setCapacity(number);
    }

    public void viewDetail() throws SQLException {
        System.out.println("Tên bàn: " + this.tableName);
        System.out.print("Sức chứa: " + this.getCapacity());
        System.out.println("Trạng thái: " + this.status);
    }

    @Override
    public String toString() {
        return String.format("\t%s\t\t\t%d", this.getTableName(), this.getCapacity());
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
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
