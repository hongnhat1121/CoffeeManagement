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
public class Product {

    private String productID;
    private String productName;
    private long unitPrice;
    private Category category;

    public Product() {
    }

    public Product(String productID, String productName, long unitPrice, Category category) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public void viewDetail() throws SQLException {
        System.out.println("Tên sản phẩm: " + this.getProductName());
        System.out.println("Đơn giá: " + this.getUnitPrice());
        System.out.println("Danh mục: " + this.getCategory().getContent());
    }

    @Override
    public String toString() {
        return String.format("%d\t%s", this.getUnitPrice(), this.getProductName());
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
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

}
