package com.popseven.livecricketscore.Model.MatchDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apis {

    @SerializedName("points_table")
    @Expose
    private String pointsTable;
    @SerializedName("commentary")
    @Expose
    private String commentary;
    @SerializedName("twitter_timeline")
    @Expose
    private String twitterTimeline;
    @SerializedName("match_timeline")
    @Expose
    private String matchTimeline;
    @SerializedName("scorecard")
    @Expose
    private String scorecard;
    @SerializedName("mini")
    @Expose
    private String mini;
    @SerializedName("over_commentary")
    @Expose
    private String overCommentary;
    @SerializedName("highlights")
    @Expose
    private String highlights;
    @SerializedName("mini_highlights")
    @Expose
    private String miniHighlights;
    @SerializedName("graphs")
    @Expose
    private String graphs;
    @SerializedName("leanback")
    @Expose
    private String leanback;
    @SerializedName("matchfacts")
    @Expose
    private String matchfacts;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("expert")
    @Expose
    private String expert;

    public String getPointsTable() {
        return pointsTable;
    }

    public void setPointsTable(String pointsTable) {
        this.pointsTable = pointsTable;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getTwitterTimeline() {
        return twitterTimeline;
    }

    public void setTwitterTimeline(String twitterTimeline) {
        this.twitterTimeline = twitterTimeline;
    }

    public String getMatchTimeline() {
        return matchTimeline;
    }

    public void setMatchTimeline(String matchTimeline) {
        this.matchTimeline = matchTimeline;
    }

    public String getScorecard() {
        return scorecard;
    }

    public void setScorecard(String scorecard) {
        this.scorecard = scorecard;
    }

    public String getMini() {
        return mini;
    }

    public void setMini(String mini) {
        this.mini = mini;
    }

    public String getOverCommentary() {
        return overCommentary;
    }

    public void setOverCommentary(String overCommentary) {
        this.overCommentary = overCommentary;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getMiniHighlights() {
        return miniHighlights;
    }

    public void setMiniHighlights(String miniHighlights) {
        this.miniHighlights = miniHighlights;
    }

    public String getGraphs() {
        return graphs;
    }

    public void setGraphs(String graphs) {
        this.graphs = graphs;
    }

    public String getLeanback() {
        return leanback;
    }

    public void setLeanback(String leanback) {
        this.leanback = leanback;
    }

    public String getMatchfacts() {
        return matchfacts;
    }

    public void setMatchfacts(String matchfacts) {
        this.matchfacts = matchfacts;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

}
