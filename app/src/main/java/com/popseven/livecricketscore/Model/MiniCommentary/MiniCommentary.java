
package com.popseven.livecricketscore.Model.MiniCommentary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MiniCommentary {

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
    @SerializedName("over_summary")
    @Expose
    private OverSummary overSummary;
    @SerializedName("bat_team")
    @Expose
    private BatTeam batTeam;
    @SerializedName("bow_team")
    @Expose
    private BowTeam bowTeam;
    @SerializedName("batsman")
    @Expose
    private List<Batsman> batsman = null;
    @SerializedName("bowler")
    @Expose
    private List<Bowler> bowler = null;
    @SerializedName("crr")
    @Expose
    private String crr;
    @SerializedName("rrr")
    @Expose
    private String rrr;
    @SerializedName("overs_left")
    @Expose
    private String oversLeft;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("prtshp")
    @Expose
    private String prtshp;
    @SerializedName("last_wkt")
    @Expose
    private String lastWkt;
    @SerializedName("last_wkt_name")
    @Expose
    private String lastWktName;
    @SerializedName("last_wkt_score")
    @Expose
    private String lastWktScore;
    @SerializedName("comm_lines")
    @Expose
    private List<CommLine> commLines = null;
    @SerializedName("range")
    @Expose
    private String range;
    @SerializedName("pulltoRefreshStopRate")
    @Expose
    private Integer pulltoRefreshStopRate;
    @SerializedName("burst_cache_id")
    @Expose
    private Integer burstCacheId;
    @SerializedName("burst_cache")
    @Expose
    private Boolean burstCache;
    @SerializedName("burst_cache_time")
    @Expose
    private Integer burstCacheTime;
    @SerializedName("ads")
    @Expose
    private Ads ads;

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

    public OverSummary getOverSummary() {
        return overSummary;
    }

    public void setOverSummary(OverSummary overSummary) {
        this.overSummary = overSummary;
    }

    public BatTeam getBatTeam() {
        return batTeam;
    }

    public void setBatTeam(BatTeam batTeam) {
        this.batTeam = batTeam;
    }

    public BowTeam getBowTeam() {
        return bowTeam;
    }

    public void setBowTeam(BowTeam bowTeam) {
        this.bowTeam = bowTeam;
    }

    public List<Batsman> getBatsman() {
        return batsman;
    }

    public void setBatsman(List<Batsman> batsman) {
        this.batsman = batsman;
    }

    public List<Bowler> getBowler() {
        return bowler;
    }

    public void setBowler(List<Bowler> bowler) {
        this.bowler = bowler;
    }

    public String getCrr() {
        return crr;
    }

    public void setCrr(String crr) {
        this.crr = crr;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }

    public String getOversLeft() {
        return oversLeft;
    }

    public void setOversLeft(String oversLeft) {
        this.oversLeft = oversLeft;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPrtshp() {
        return prtshp;
    }

    public void setPrtshp(String prtshp) {
        this.prtshp = prtshp;
    }

    public String getLastWkt() {
        return lastWkt;
    }

    public void setLastWkt(String lastWkt) {
        this.lastWkt = lastWkt;
    }

    public String getLastWktName() {
        return lastWktName;
    }

    public void setLastWktName(String lastWktName) {
        this.lastWktName = lastWktName;
    }

    public String getLastWktScore() {
        return lastWktScore;
    }

    public void setLastWktScore(String lastWktScore) {
        this.lastWktScore = lastWktScore;
    }

    public List<CommLine> getCommLines() {
        return commLines;
    }

    public void setCommLines(List<CommLine> commLines) {
        this.commLines = commLines;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getPulltoRefreshStopRate() {
        return pulltoRefreshStopRate;
    }

    public void setPulltoRefreshStopRate(Integer pulltoRefreshStopRate) {
        this.pulltoRefreshStopRate = pulltoRefreshStopRate;
    }

    public Integer getBurstCacheId() {
        return burstCacheId;
    }

    public void setBurstCacheId(Integer burstCacheId) {
        this.burstCacheId = burstCacheId;
    }

    public Boolean getBurstCache() {
        return burstCache;
    }

    public void setBurstCache(Boolean burstCache) {
        this.burstCache = burstCache;
    }

    public Integer getBurstCacheTime() {
        return burstCacheTime;
    }

    public void setBurstCacheTime(Integer burstCacheTime) {
        this.burstCacheTime = burstCacheTime;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

}
