/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.nthn.pojo;

/**
 *
 * @author HONGNHAT
 */
public enum State {
    SERVE(1, "Phục vụ"), BARTENDER(2, "Pha chế"), RECEPTION(3, "Tiếp tân");
    private int stateID;
    private String stateName;

    private State() {
    }

    private State(int stateID, String stateName) {
        this.stateID = stateID;
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return this.stateName;
    }
    

    /**
     * @return the stateID
     */
    public int getStateID() {
        return stateID;
    }

    /**
     * @param stateID the stateID to set
     */
    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
