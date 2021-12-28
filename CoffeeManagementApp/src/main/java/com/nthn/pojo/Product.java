/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public abstract class Product {

    private int productID;
    private String productName;
    private long unitPrice;
    private Category category;

    public Product() {
    }

    public Product(int productID, String productName, long unitPrice, Category category) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public void edit(long price) {
        this.unitPrice = price;
    }

    public void edit(String name) {
        this.productName = name;
    }

    public void edit(Category category) {
        this.category = category;
    }
    
    public void viewDetail(){
        System.out.println("Tên sản phẩm: " + this.productName);
        System.out.println("Đơn giá: " + this.unitPrice);
        System.out.println("Danh mục: " + this.category.toString());
    }

    @Override
    public String toString() {
        return String.format("%s\t%d", this.productName, this.unitPrice);
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
