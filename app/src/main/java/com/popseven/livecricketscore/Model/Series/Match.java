
package com.popseven.livecricketscore.Model.Series;

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
    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("team1")
    @Expose
    private Team1 team1;
    @SerializedName("team2")
    @Expose
    private Team2 team2;
    @SerializedName("score")
    @Expose
    private List<Score> score = null;
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

    public List<Score> getScore() {
        return score;
    }

    public void setScore(List<Score> score) {
        this.score = score;
    }

    public List<Integer> getSrsCategory() {
        return srsCategory;
    }

    public void setSrsCategory(List<Integer> srsCategory) {
        this.srsCategory = srsCategory;
    }

}
