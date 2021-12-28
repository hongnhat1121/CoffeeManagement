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
    MALE(1, "Nam"), FEMALE(2, "Nữ"), OTHER(3, "Khác");
    private final int genderID;
    private final String genderName;

    private Gender(int genderID, String genderName) {
        this.genderID = genderID;
        this.genderName = genderName;
    }

    @Override
    public String toString() {
        return this.getGenderName();
    }

    /**
     * @return the genderID
     */
    public int getGenderID() {
        return genderID;
    }

    /**
     * @return the genderName
     */
    public String getGenderName() {
        return genderName;
    }
}
