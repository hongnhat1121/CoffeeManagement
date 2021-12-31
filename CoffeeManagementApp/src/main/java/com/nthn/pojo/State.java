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
    SERVE("Phục vụ"), BARTENDER("Pha chế"), RECEPTION("Tiếp tân");
    private final String content;

    private State(String content) {
        this.content = content;
    }

    public static State getByContent(String text) {
        switch (text) {
            case "Phục vụ":
                return SERVE;
            case "Pha chế":
                return BARTENDER;
            case "Tiếp tân":
                return RECEPTION;
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
