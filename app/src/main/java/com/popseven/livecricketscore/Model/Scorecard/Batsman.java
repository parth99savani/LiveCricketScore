
package com.popseven.livecricketscore.Model.Scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batsman {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("out_desc")
    @Expose
    private String outDesc;
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

    public String getOutDesc() {
        return outDesc;
    }

    public void setOutDesc(String outDesc) {
        this.outDesc = outDesc;
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
