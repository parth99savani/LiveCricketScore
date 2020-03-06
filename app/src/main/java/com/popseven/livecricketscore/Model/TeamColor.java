package com.popseven.livecricketscore.Model;

public class TeamColor {
    private String id;
    private String colorCode;

    public TeamColor(String id, String colorCode) {
        this.id = id;
        this.colorCode = colorCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
}
