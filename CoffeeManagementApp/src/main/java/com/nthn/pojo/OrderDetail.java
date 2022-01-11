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
public class OrderDetail {

    private String orderID;
    private String productID;
    private String productName;
    private int quantity;
    private long unitPrice;
    private String note;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String productID, String productName, int quantity, long unitPrice, String note) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.note = note;
    }

    

    public void changeQuantity(int number) {
        this.setQuantity(getQuantity()+number);
    }

    public void changeNote(String text) {
        this.setNote(text);
    }

    public void viewDetail() throws SQLException {
        System.out.println(this.getProductID());
        System.out.println("Số lượng: " + this.getQuantity());
        System.out.println("Đơn giá: " + this.getUnitPrice());
        System.out.println("Ghi chú: " + this.getNote());
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s", this.getQuantity(), this.getUnitPrice(), this.getNote());
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
     * @return the productID
     */
    public String getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitPrice
     */
    public long getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

}
