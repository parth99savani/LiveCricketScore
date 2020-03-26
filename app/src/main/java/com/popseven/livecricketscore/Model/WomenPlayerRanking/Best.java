
package com.popseven.livecricketscore.Model.WomenPlayerRanking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Best {

    @SerializedName("match")
    @Expose
    private Match match;
    @SerializedName("rating")
    @Expose
    private Integer rating;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
