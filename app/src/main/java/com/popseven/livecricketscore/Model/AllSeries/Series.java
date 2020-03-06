
package com.popseven.livecricketscore.Model.AllSeries;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("series_id")
    @Expose
    private String seriesId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("teams")
    @Expose
    private List<String> teams = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

}
