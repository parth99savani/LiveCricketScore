
package com.popseven.livecricketscore.Model.LiveMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url {

    @SerializedName("match")
    @Expose
    private String match;

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

}
