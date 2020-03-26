
package com.popseven.livecricketscore.Model.WomenPlayerRanking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("player")
    @Expose
    private Player player;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("best")
    @Expose
    private Best best;
    @SerializedName("previousRanking")
    @Expose
    private Integer previousRanking;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Best getBest() {
        return best;
    }

    public void setBest(Best best) {
        this.best = best;
    }

    public Integer getPreviousRanking() {
        return previousRanking;
    }

    public void setPreviousRanking(Integer previousRanking) {
        this.previousRanking = previousRanking;
    }

}
