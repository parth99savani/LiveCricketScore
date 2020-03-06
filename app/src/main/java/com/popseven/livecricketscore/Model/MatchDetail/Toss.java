
package com.popseven.livecricketscore.Model.MatchDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Toss {

    @SerializedName("winner")
    @Expose
    private String winner;
    @SerializedName("decision")
    @Expose
    private String decision;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

}
