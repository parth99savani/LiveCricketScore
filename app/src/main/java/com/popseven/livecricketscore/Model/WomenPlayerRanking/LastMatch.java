
package com.popseven.livecricketscore.Model.WomenPlayerRanking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastMatch {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("teamA")
    @Expose
    private TeamA teamA;
    @SerializedName("teamB")
    @Expose
    private TeamB teamB;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public TeamA getTeamA() {
        return teamA;
    }

    public void setTeamA(TeamA teamA) {
        this.teamA = teamA;
    }

    public TeamB getTeamB() {
        return teamB;
    }

    public void setTeamB(TeamB teamB) {
        this.teamB = teamB;
    }

}
