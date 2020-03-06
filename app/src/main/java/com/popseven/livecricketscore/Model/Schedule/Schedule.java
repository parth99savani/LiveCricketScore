package com.popseven.livecricketscore.Model.Schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule {
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;
    @SerializedName("country")
    @Expose
    private List<Country> country = null;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("series")
    @Expose
    private List<Series> series = null;
    @SerializedName("tabs")
    @Expose
    private List<Tab> tabs = null;
    @SerializedName("filters")
    @Expose
    private List<Filter> filters = null;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

}
