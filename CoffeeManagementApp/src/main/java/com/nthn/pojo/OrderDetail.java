/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.services.ProductService;
import java.sql.SQLException;

/**
 *
 * @author HONGNHAT
 */
public class OrderDetail {

    private int orderID;
    private int productID;
    private int quantity;
    private long unitPrice;
    private String note;

    public OrderDetail() {
    }

    public OrderDetail(int orderID, int productID, int quantity, long unitPrice, String note) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.note = note;
    }

    public void changeProduct(int id) {
        this.setProductID(id);
    }

    public void changeQuantity(int number) {
        this.setQuantity(number);
    }

    public void changeNote(String text) {
        this.setNote(text);
    }

    public void viewDetail() throws SQLException {
        System.out.println(new ProductService().getProduct(productID).toString());
        System.out.println("Số lượng: " + this.quantity);
        System.out.println("Đơn giá: " + this.unitPrice);
        System.out.println("Ghi chú: " + this.note);
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
    public float getUnitPrice() {
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
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }
}
