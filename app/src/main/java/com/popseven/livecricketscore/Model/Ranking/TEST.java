
package com.popseven.livecricketscore.Model.Ranking;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TEST {

    @SerializedName("batting")
    @Expose
    private List<Batting> batting = null;
    @SerializedName("bowling")
    @Expose
    private List<Batting> bowling = null;
    @SerializedName("allrounder")
    @Expose
    private List<Batting> allrounder = null;

    public List<Batting> getBatting() {
        return batting;
    }

    public void setBatting(List<Batting> batting) {
        this.batting = batting;
    }

    public List<Batting> getBowling() {
        return bowling;
    }

    public void setBowling(List<Batting> bowling) {
        this.bowling = bowling;
    }

    public List<Batting> getAllrounder() {
        return allrounder;
    }

    public void setAllrounder(List<Batting> allrounder) {
        this.allrounder = allrounder;
    }

}
