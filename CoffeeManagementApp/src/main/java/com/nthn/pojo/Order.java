/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author HONGNHAT
 */
public class Order {

    private int orderID;
    private Date orderDate;
    private long total;
    private Employee employee;
    private Table table;
    private boolean payment;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(int orderID, Date orderDate, long total, Employee employee, Table table, boolean payment) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.employee = employee;
        this.table = table;
        this.payment = payment;
    }

    public Order(int orderID, Date orderDate, long total, Employee employee, Table table, boolean payment, List<OrderDetail> orderDetails) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.employee = employee;
        this.table = table;
        this.payment = payment;
        this.orderDetails = orderDetails;
    }

    //Tính toán hoá đơn
    public void calculate() {
        for (OrderDetail orderDetail : orderDetails) {
            this.total += orderDetail.getQuantity() * orderDetail.getUnitPrice();
        }
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * @return the payment
     */
    public boolean isPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(boolean payment) {
        this.payment = payment;
    }

}
