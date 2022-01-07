/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public enum Gender {
    MALE("Nam"), FEMALE("Nữ"), OTHER("Khác");

    
    private final String content;

    private Gender(String content) {
        this.content = content;
    }

    public static Gender getByContent(String text) {
        switch (text) {
            case "Nam":
                return MALE;
            case "Nữ":
                return FEMALE;
            case "Khác":
                return OTHER;
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
