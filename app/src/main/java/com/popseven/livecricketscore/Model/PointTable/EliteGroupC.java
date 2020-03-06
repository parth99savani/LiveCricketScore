
package com.popseven.livecricketscore.Model.PointTable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EliteGroupC {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("w")
    @Expose
    private String w;
    @SerializedName("l")
    @Expose
    private String l;
    @SerializedName("nr")
    @Expose
    private String nr;
    @SerializedName("t")
    @Expose
    private String t;
    @SerializedName("points")
    @Expose
    private String points;
    @SerializedName("nrr")
    @Expose
    private String nrr;

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

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getNrr() {
        return nrr;
    }

    public void setNrr(String nrr) {
        this.nrr = nrr;
    }

}
