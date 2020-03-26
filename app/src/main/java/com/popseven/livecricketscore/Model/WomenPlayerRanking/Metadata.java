
package com.popseven.livecricketscore.Model.WomenPlayerRanking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("lastMatch")
    @Expose
    private LastMatch lastMatch;

    public LastMatch getLastMatch() {
        return lastMatch;
    }

    public void setLastMatch(LastMatch lastMatch) {
        this.lastMatch = lastMatch;
    }

}
