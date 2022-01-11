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
    FOOD("Thức ăn"), DRINK("Đồ uống");
    private final String content;

    private Category(String content) {
        this.content = content;
    }

    public static Category getByContent(String text) {
        switch (text) {
            case "Thức ăn":
                return FOOD;
            case "Đồ uống":
                return DRINK;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.content;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

}
