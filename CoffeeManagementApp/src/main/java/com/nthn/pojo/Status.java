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
    EMPTY(1, "Còn trống"), FULL(2, "Đã đặt");
    private final int statusID;
    private final String statusName;

    Status(int statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
    }

    public static Status getStatusByID(int id) {
        switch (id) {
            case 1:
                return EMPTY;
            case 2:
                return FULL;
        }
        return null;
    }

    /**
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @return the statusName
     */
    public String getStatusName() {
        return statusName;
    }

}
