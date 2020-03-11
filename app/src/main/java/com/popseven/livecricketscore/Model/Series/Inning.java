
package com.popseven.livecricketscore.Model.Series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inning {

    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("wkts")
    @Expose
    private String wkts;
    @SerializedName("overs")
    @Expose
    private String overs;
    @SerializedName("currentbowlteamscore")
    @Expose
    private String currentbowlteamscore;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWkts() {
        return wkts;
    }

    public void setWkts(String wkts) {
        this.wkts = wkts;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    public String getCurrentbowlteamscore() {
        return currentbowlteamscore;
    }

    public void setCurrentbowlteamscore(String currentbowlteamscore) {
        this.currentbowlteamscore = currentbowlteamscore;
    }
}
