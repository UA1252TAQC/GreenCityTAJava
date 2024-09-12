package com.academy.ui.styles;

public enum ColorConstants {

    PRIMARYGREEN("#13aa57");

    private final String hex;

    ColorConstants(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return this.hex;
    }
}
