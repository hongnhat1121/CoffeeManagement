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
    ADMIN(1, "Người quản trị"),USER(2,"Người dùng");

    private final int roleID;
    private final String roleName;

    Role() {
        this.roleID = 0;
        this.roleName = null;
    }

    Role(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }
    
    
    @Override
    public String toString() {
        return this.getRoleName();
    }

    /**
     * @return the roleID
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }
    
    
    
}
