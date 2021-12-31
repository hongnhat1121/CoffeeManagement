/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

import com.nthn.services.CategoryService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HONGNHAT
 */
public class Product {
    
    private String productID;
    private String productName;
    private long unitPrice;
    private int categoryId;
    
    public Product() {
    }

    public Product(String productID, String productName, long unitPrice, int categoryId) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.categoryId = categoryId;
    }
    
    
    public void viewDetail() throws SQLException {
        System.out.println("Tên sản phẩm: " + this.getProductName());
        System.out.println("Đơn giá: " + this.getUnitPrice());
        System.out.println("Danh mục: " + new CategoryService().getCatagory(getCategoryId()));
    }
    
    @Override
    public String toString() {
        try {
            return String.format("%s\t%s", this.getProductName(), new CategoryService().getCatagory(getCategoryId()));
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    
}
