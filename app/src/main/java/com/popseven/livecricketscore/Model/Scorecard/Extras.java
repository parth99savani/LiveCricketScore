
package com.popseven.livecricketscore.Model.Scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extras {

    @SerializedName("t")
    @Expose
    private String t;
    @SerializedName("b")
    @Expose
    private String b;
    @SerializedName("lb")
    @Expose
    private String lb;
    @SerializedName("wd")
    @Expose
    private String wd;
    @SerializedName("nb")
    @Expose
    private String nb;
    @SerializedName("p")
    @Expose
    private String p;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getNb() {
        return nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

}
