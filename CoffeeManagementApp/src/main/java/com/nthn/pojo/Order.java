/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.configs.Utils;
import com.nthn.services.EmployeeService;
import com.nthn.services.TableService;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class Order {

    private String orderID;
    private Date orderDate;
    private BigDecimal total;
    private String employeeID;
    private String tableID;
    private int payment;

    public Order() {
    }

    public Order(String orderID, Date orderDate, BigDecimal total, String employeeID, String tableID, int payment) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.employeeID = employeeID;
        this.tableID = tableID;
        this.payment = payment;
    }

//
//    //Tính toán hoá đơn
//    public void calculate() {
//        getOrderDetails().forEach(orderDetail -> {
//            this.setTotal((long) (this.getTotal() + orderDetail.getQuantity()
//                    * orderDetail.getUnitPrice()));
//        });
//    }
    public void viewDetail() throws SQLException {
        System.out.println("Mã hoá đơn: " + this.getOrderID());
        System.out.println("Ngày tạo: " + Utils.DATEFORMAT.format(getOrderDate()));
        System.out.println("Tổng tiền: " + this.getTotal());
        System.out.println("Nhân viên:" + new EmployeeService().
                getEmployee(getEmployeeID()).getLastName());
        System.out.println("Bàn: " + new TableService().getTable(getTableID()).toString());
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
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
     * @return the payment
     */
    public int getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(int payment) {
        this.payment = payment;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
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
