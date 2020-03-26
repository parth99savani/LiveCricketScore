
package com.popseven.livecricketscore.Model.Ranking;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("TEST")
    @Expose
    private List<TEST_> tEST = null;
    @SerializedName("ODI")
    @Expose
    private List<TEST_> oDI = null;
    @SerializedName("T20")
    @Expose
    private List<TEST_> t20 = null;

    public List<TEST_> getTEST() {
        return tEST;
    }

    public void setTEST(List<TEST_> tEST) {
        this.tEST = tEST;
    }

    public List<TEST_> getODI() {
        return oDI;
    }

    public void setODI(List<TEST_> oDI) {
        this.oDI = oDI;
    }

    public List<TEST_> getT20() {
        return t20;
    }

    public void setT20(List<TEST_> t20) {
        this.t20 = t20;
    }

}
