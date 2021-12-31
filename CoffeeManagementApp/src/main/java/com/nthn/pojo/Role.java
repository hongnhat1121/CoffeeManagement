/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public enum Role {
    ADMIN("Người quản trị"), USER("Người dùng");
    private final String content;

    private Role(String content) {
        this.content = content;
    }

    

    public static Role getByContent(String text) {
        switch (text) {
            case "Người quản trị":
                return ADMIN;
            case "Người dùng":
                return USER;
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
