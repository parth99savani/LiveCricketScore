package com.popseven.livecricketscore.Model.MatchDetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchDetail {

    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("series_id")
    @Expose
    private String seriesId;
    @SerializedName("series_name")
    @Expose
    private String seriesName;
    @SerializedName("data_path")
    @Expose
    private String dataPath;
    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("alerts")
    @Expose
    private String alerts;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("official")
    @Expose
    private Official official;
    @SerializedName("toss")
    @Expose
    private Toss toss;
    @SerializedName("audio")
    @Expose
    private List<Object> audio = null;
    @SerializedName("team1")
    @Expose
    private Team1 team1;
    @SerializedName("team2")
    @Expose
    private Team2 team2;
    @SerializedName("players")
    @Expose
    private List<Player> players = null;
    @SerializedName("apis")
    @Expose
    private Apis apis;
    @SerializedName("ads")
    @Expose
    private List<Object> ads = null;

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

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

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Official getOfficial() {
        return official;
    }

    public void setOfficial(Official official) {
        this.official = official;
    }

    public Toss getToss() {
        return toss;
    }

    public void setToss(Toss toss) {
        this.toss = toss;
    }

    public List<Object> getAudio() {
        return audio;
    }

    public void setAudio(List<Object> audio) {
        this.audio = audio;
    }

    public Team1 getTeam1() {
        return team1;
    }

    public void setTeam1(Team1 team1) {
        this.team1 = team1;
    }

    public Team2 getTeam2() {
        return team2;
    }

    public void setTeam2(Team2 team2) {
        this.team2 = team2;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Apis getApis() {
        return apis;
    }

    public void setApis(Apis apis) {
        this.apis = apis;
    }

    public List<Object> getAds() {
        return ads;
    }

    public void setAds(List<Object> ads) {
        this.ads = ads;
    }

}
