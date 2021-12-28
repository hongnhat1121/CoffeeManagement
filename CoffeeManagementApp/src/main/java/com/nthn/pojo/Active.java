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
    AVAILABLE(1, "Đang hoạt động"), LOCK(2,"Bị khoá");
    private final int activeId;
    private final String acitveName;

    private Active(int activeId, String acitveText) {
        this.activeId = activeId;
        this.acitveName = acitveText;
    }

    @Override
    public String toString() {
        return acitveName;
    }
    
    
}
