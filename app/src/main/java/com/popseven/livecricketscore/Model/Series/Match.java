
package com.popseven.livecricketscore.Model.Series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("series_id")
    @Expose
    private String seriesId;
    @SerializedName("series_name")
    @Expose
    private String seriesName;
    @SerializedName("data_path")
    @Expose
    private String dataPath;
    @SerializedName("alerts")
    @Expose
    private String alerts;
    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("team1_name")
    @Expose
    private String team1Name;
    @SerializedName("team2_name")
    @Expose
    private String team2Name;
    @SerializedName("bat_team")
    @Expose
    private BatTeam batTeam;
    @SerializedName("bow_team")
    @Expose
    private BowTeam bowTeam;
    @SerializedName("team1")
    @Expose
    private Team1 team1;
    @SerializedName("team2")
    @Expose
    private Team2 team2;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public BatTeam getBatTeam() {
        return batTeam;
    }

    public void setBatTeam(BatTeam batTeam) {
        this.batTeam = batTeam;
    }

    public BowTeam getBowTeam() {
        return bowTeam;
    }

    public void setBowTeam(BowTeam bowTeam) {
        this.bowTeam = bowTeam;
    }

    public Team1 getTeam1() {
        return team1;
    }

    public void setTeam1(Team1 team1) {
        this.team1 = team1;
    }

    public Team2 getTeam2() {
        return team2;
    }

    public void setTeam2(Team2 team2) {
        this.team2 = team2;
    }

}
