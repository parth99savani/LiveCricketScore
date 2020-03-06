
package com.popseven.livecricketscore.Model.LiveMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batsman {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("strike")
    @Expose
    private String strike;
    @SerializedName("r")
    @Expose
    private String r;
    @SerializedName("b")
    @Expose
    private String b;
    @SerializedName("4s")
    @Expose
    private String _4s;
    @SerializedName("6s")
    @Expose
    private String _6s;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrike() {
        return strike;
    }

    public void setStrike(String strike) {
        this.strike = strike;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String get4s() {
        return _4s;
    }

    public void set4s(String _4s) {
        this._4s = _4s;
    }

    public String get6s() {
        return _6s;
    }

    public void set6s(String _6s) {
        this._6s = _6s;
    }

}
