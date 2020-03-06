
package com.popseven.livecricketscore.Model.Scorecard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inning {

    @SerializedName("innings_id")
    @Expose
    private String inningsId;
    @SerializedName("bat_team_id")
    @Expose
    private String batTeamId;
    @SerializedName("bat_team_name")
    @Expose
    private String batTeamName;
    @SerializedName("bowl_team_id")
    @Expose
    private String bowlTeamId;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("wkts")
    @Expose
    private String wkts;
    @SerializedName("ovr")
    @Expose
    private String ovr;
    @SerializedName("batsmen")
    @Expose
    private List<Batsman> batsmen = null;
    @SerializedName("next_batsman")
    @Expose
    private String nextBatsman;
    @SerializedName("next_batsman_label")
    @Expose
    private String nextBatsmanLabel;
    @SerializedName("bowlers")
    @Expose
    private List<Bowler> bowlers = null;
    @SerializedName("extras")
    @Expose
    private Extras extras;
    @SerializedName("fow")
    @Expose
    private List<Fow> fow = null;

    public String getInningsId() {
        return inningsId;
    }

    public void setInningsId(String inningsId) {
        this.inningsId = inningsId;
    }

    public String getBatTeamId() {
        return batTeamId;
    }

    public void setBatTeamId(String batTeamId) {
        this.batTeamId = batTeamId;
    }

    public String getBatTeamName() {
        return batTeamName;
    }

    public void setBatTeamName(String batTeamName) {
        this.batTeamName = batTeamName;
    }

    public String getBowlTeamId() {
        return bowlTeamId;
    }

    public void setBowlTeamId(String bowlTeamId) {
        this.bowlTeamId = bowlTeamId;
    }

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

    public String getOvr() {
        return ovr;
    }

    public void setOvr(String ovr) {
        this.ovr = ovr;
    }

    public List<Batsman> getBatsmen() {
        return batsmen;
    }

    public void setBatsmen(List<Batsman> batsmen) {
        this.batsmen = batsmen;
    }

    public String getNextBatsman() {
        return nextBatsman;
    }

    public void setNextBatsman(String nextBatsman) {
        this.nextBatsman = nextBatsman;
    }

    public String getNextBatsmanLabel() {
        return nextBatsmanLabel;
    }

    public void setNextBatsmanLabel(String nextBatsmanLabel) {
        this.nextBatsmanLabel = nextBatsmanLabel;
    }

    public List<Bowler> getBowlers() {
        return bowlers;
    }

    public void setBowlers(List<Bowler> bowlers) {
        this.bowlers = bowlers;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    public List<Fow> getFow() {
        return fow;
    }

    public void setFow(List<Fow> fow) {
        this.fow = fow;
    }

}
