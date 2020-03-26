
package com.popseven.livecricketscore.Model.Ranking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("TEST")
    @Expose
    private TEST tEST;
    @SerializedName("ODI")
    @Expose
    private ODI oDI;
    @SerializedName("T20")
    @Expose
    private T20 t20;

    public TEST getTEST() {
        return tEST;
    }

    public void setTEST(TEST tEST) {
        this.tEST = tEST;
    }

    public ODI getODI() {
        return oDI;
    }

    public void setODI(ODI oDI) {
        this.oDI = oDI;
    }

    public T20 getT20() {
        return t20;
    }

    public void setT20(T20 t20) {
        this.t20 = t20;
    }

}
