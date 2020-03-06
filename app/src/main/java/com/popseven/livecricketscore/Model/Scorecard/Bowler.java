
package com.popseven.livecricketscore.Model.Scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bowler {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("o")
    @Expose
    private String o;
    @SerializedName("m")
    @Expose
    private String m;
    @SerializedName("r")
    @Expose
    private String r;
    @SerializedName("w")
    @Expose
    private String w;
    @SerializedName("n")
    @Expose
    private String n;
    @SerializedName("wd")
    @Expose
    private String wd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

}