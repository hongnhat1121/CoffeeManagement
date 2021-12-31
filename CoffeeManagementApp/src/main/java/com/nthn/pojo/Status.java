/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public enum Status {
    EMPTY("Còn trống"), FULL("Đã đặt");
    private final String content;

    private Status(String content) {
        this.content = content;
    }

    public static Status getByContent(String text) {
        switch (text) {
            case "Còn trống":
                return EMPTY;
            case "Đã đặt":
                return FULL;
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
