/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.configs.Utils;
import com.nthn.services.EmployeeService;
import com.nthn.services.TableService;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HONGNHAT
 */
public class Order {

    private int orderID;
    private Date orderDate;
    private long total;
    private int employeeID;
    private int tableID;
    private boolean payment;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(int orderID, Date orderDate, long total, int employeeID,
            int tableID, boolean payment, List<OrderDetail> orderDetails) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.employeeID = employeeID;
        this.tableID = tableID;
        this.payment = payment;
        this.orderDetails = orderDetails;
    }

    //Tính toán hoá đơn
    public void calculate() {
        getOrderDetails().forEach(orderDetail -> {
            this.setTotal((long) (this.getTotal() + orderDetail.getQuantity()
                    * orderDetail.getUnitPrice()));
        });
    }

    public void viewDetail() throws SQLException {
        System.out.println("Mã hoá đơn: " + this.orderID);
        System.out.println("Ngày tạo: " + Utils.DATEFORMAT.format(orderDate));
        System.out.println("Tổng tiền: " + this.total);
        System.out.println("Nhân viên:" + new EmployeeService().
                getEmployee(employeeID).getLastName());
        System.out.println("Bàn: " + new TableService().getTable(orderID).toString());
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

    /**
     * @return the orderDetails
     */
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    /**
     * @param orderDetails the orderDetails to set
     */
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
