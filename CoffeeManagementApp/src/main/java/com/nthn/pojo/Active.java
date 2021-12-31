/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public enum Active {
    AVAILABLE("Đang hoạt động"), LOCK("Bị khoá");
    private final String content;

    private Active(String content) {
        this.content = content;
    }

    public static Active getByContent(String text) {
        switch (text) {
            case "Đang hoạt động":
                return AVAILABLE;
            case "Bị khoá":
                return LOCK;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getContent();
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
