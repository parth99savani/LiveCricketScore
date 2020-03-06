
package com.popseven.livecricketscore.Model.LiveMatches;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Livematches {

    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;
    @SerializedName("srs_category")
    @Expose
    private List<SrsCategory> srsCategory = null;

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<SrsCategory> getSrsCategory() {
        return srsCategory;
    }

    public void setSrsCategory(List<SrsCategory> srsCategory) {
        this.srsCategory = srsCategory;
    }

}
