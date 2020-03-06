
package com.popseven.livecricketscore.Model.LiveMatches;

import java.util.List;
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
    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("alerts")
    @Expose
    private String alerts;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("bat_team")
    @Expose
    private BatTeam batTeam = null;
    @SerializedName("bow_team")
    @Expose
    private BowTeam bowTeam = null;
    @SerializedName("batsman")
    @Expose
    private List<Batsman> batsman = null;
    @SerializedName("bowler")
    @Expose
    private List<Bowler> bowler = null;
    @SerializedName("team1")
    @Expose
    private Team1 team1;
    @SerializedName("team2")
    @Expose
    private Team2 team2;
    @SerializedName("srs_category")
    @Expose
    private List<Integer> srsCategory = null;

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

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
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

    public List<Batsman> getBatsman() {
        return batsman;
    }

    public void setBatsman(List<Batsman> batsman) {
        this.batsman = batsman;
    }

    public List<Bowler> getBowler() {
        return bowler;
    }

    public void setBowler(List<Bowler> bowler) {
        this.bowler = bowler;
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

    public List<Integer> getSrsCategory() {
        return srsCategory;
    }

    public void setSrsCategory(List<Integer> srsCategory) {
        this.srsCategory = srsCategory;
    }

}
