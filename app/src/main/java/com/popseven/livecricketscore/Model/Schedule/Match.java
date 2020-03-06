package com.popseven.livecricketscore.Model.Schedule;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("series_id")
    @Expose
    private String seriesId;
    @SerializedName("t_desc")
    @Expose
    private String tDesc;
    @SerializedName("m_desc")
    @Expose
    private String mDesc;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("day_end_time")
    @Expose
    private String dayEndTime;
    @SerializedName("time_zone")
    @Expose
    private String timeZone;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("alerts")
    @Expose
    private String alerts;
    @SerializedName("team")
    @Expose
    private List<Integer> team = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("country")
    @Expose
    private List<Integer> country = null;
    @SerializedName("tabs")
    @Expose
    private List<Integer> tabs = null;
    @SerializedName("tab_category")
    @Expose
    private Integer tabCategory;
    @SerializedName("dn")
    @Expose
    private String dn;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getTDesc() {
        return tDesc;
    }

    public void setTDesc(String tDesc) {
        this.tDesc = tDesc;
    }

    public String getMDesc() {
        return mDesc;
    }

    public void setMDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(String dayEndTime) {
        this.dayEndTime = dayEndTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public List<Integer> getTeam() {
        return team;
    }

    public void setTeam(List<Integer> team) {
        this.team = team;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getCountry() {
        return country;
    }

    public void setCountry(List<Integer> country) {
        this.country = country;
    }

    public List<Integer> getTabs() {
        return tabs;
    }

    public void setTabs(List<Integer> tabs) {
        this.tabs = tabs;
    }

    public Integer getTabCategory() {
        return tabCategory;
    }

    public void setTabCategory(Integer tabCategory) {
        this.tabCategory = tabCategory;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

}
