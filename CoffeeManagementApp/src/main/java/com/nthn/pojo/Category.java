/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public enum Category {
    DRINK(1,"Thức uống"), FOOD(2,"Thức ăn");
    private final int categoryID;
    private final String categoryName;

    private Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
    
    public static Category getByContent(String text) {
        switch (text) {
            case "Thức uống":
                return DRINK;
            case "Thức ăn":
                return FOOD;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.categoryName; 
    }
    
    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

}
