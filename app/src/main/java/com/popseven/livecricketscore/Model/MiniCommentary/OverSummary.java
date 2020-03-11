
package com.popseven.livecricketscore.Model.MiniCommentary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OverSummary {

    @SerializedName("over")
    @Expose
    private String over;
    @SerializedName("ball_def")
    @Expose
    private String ballDef;
    @SerializedName("rem_over")
    @Expose
    private String remOver;
    @SerializedName("runs")
    @Expose
    private String runs;
    @SerializedName("wickets")
    @Expose
    private String wickets;
    @SerializedName("fours")
    @Expose
    private String fours;
    @SerializedName("sixes")
    @Expose
    private String sixes;

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getBallDef() {
        return ballDef;
    }

    public void setBallDef(String ballDef) {
        this.ballDef = ballDef;
    }

    public String getRemOver() {
        return remOver;
    }

    public void setRemOver(String remOver) {
        this.remOver = remOver;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public String getWickets() {
        return wickets;
    }

    public void setWickets(String wickets) {
        this.wickets = wickets;
    }

    public String getFours() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public String getSixes() {
        return sixes;
    }

    public void setSixes(String sixes) {
        this.sixes = sixes;
    }

}
