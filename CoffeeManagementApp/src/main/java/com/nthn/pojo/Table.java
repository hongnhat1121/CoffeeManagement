/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public class Table {

    private int tableID;
    private String tableName;
    private int capacity;
    private Status status;

    public Table() {
    }

    public Table(int tableID, String tableName, int capacity, Status status) {
        this.tableID = tableID;
        this.tableName = tableName;
        this.capacity = capacity;
        this.status = status;
    }

    public void edit(String text) {
        this.tableName = text;
    }

    public void edit(int number) {
        this.capacity = number;
    }

    public void changeStatus() {
        if (this.status == Status.FULL) {
            this.status = Status.EMPTY;
        } else {
            this.status = Status.FULL;
        }
    }
    
    public void viewDetail(){
        System.out.print("Sức chứa: " + this.capacity);
        System.out.println("Trạng thái: " + this.status.toString());
    }

    /**
     * @return the tableID
     */
    public int getTableID() {
        return tableID;
    }

    /**
     * @param tableID the tableID to set
     */
    public void setTableID(int tableID) {
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
