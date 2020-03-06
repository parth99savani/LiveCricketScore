
package com.popseven.livecricketscore.Model.AllSeries;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllSeries {

    @SerializedName("series")
    @Expose
    private List<Series> series = null;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
