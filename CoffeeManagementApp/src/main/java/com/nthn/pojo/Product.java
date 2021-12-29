/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public class Product {

    private int productID;
    private String productName;
    private long unitPrice;
    private int categoryId;

    public Product() {
    }

    public Product(int productID, String productName, long unitPrice, int categoryId) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }
    
    public void viewDetail(){
        System.out.println("Tên sản phẩm: " + this.getProductName());
        System.out.println("Đơn giá: " + this.getUnitPrice());
        System.out.println("Danh mục: " + this.getCategoryId());
    }

    @Override
    public String toString() {
        return String.format("%s\t%d", this.getProductName(), this.getUnitPrice());
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
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
  
    

}
