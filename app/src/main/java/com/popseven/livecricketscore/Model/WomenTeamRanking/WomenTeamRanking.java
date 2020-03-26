
package com.popseven.livecricketscore.Model.WomenTeamRanking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WomenTeamRanking {

    @SerializedName("team")
    @Expose
    private Team team;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("position")
    @Expose
    private Integer position;

    public WomenTeamRanking(Team team, Integer points, Integer rating, Integer position) {
        this.team = team;
        this.points = points;
        this.rating = rating;
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
