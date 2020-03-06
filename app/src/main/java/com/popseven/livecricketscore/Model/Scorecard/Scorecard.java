
package com.popseven.livecricketscore.Model.Scorecard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scorecard {

    @SerializedName("Innings")
    @Expose
    private List<Inning> innings = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("state")
    @Expose
    private String state;

    public List<Inning> getInnings() {
        return innings;
    }

    public void setInnings(List<Inning> innings) {
        this.innings = innings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
