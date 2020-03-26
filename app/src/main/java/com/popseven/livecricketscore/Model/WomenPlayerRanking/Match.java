
package com.popseven.livecricketscore.Model.WomenPlayerRanking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("venue")
    @Expose
    private Venue_ venue;
    @SerializedName("teamA")
    @Expose
    private TeamA_ teamA;
    @SerializedName("teamB")
    @Expose
    private TeamB_ teamB;

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

    public Venue_ getVenue() {
        return venue;
    }

    public void setVenue(Venue_ venue) {
        this.venue = venue;
    }

    public TeamA_ getTeamA() {
        return teamA;
    }

    public void setTeamA(TeamA_ teamA) {
        this.teamA = teamA;
    }

    public TeamB_ getTeamB() {
        return teamB;
    }

    public void setTeamB(TeamB_ teamB) {
        this.teamB = teamB;
    }

}
