
package com.popseven.livecricketscore.Model.MiniCommentary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommLine {

    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("wkts")
    @Expose
    private String wkts;
    @SerializedName("o_no")
    @Expose
    private String oNo;
    @SerializedName("i_id")
    @Expose
    private String iId;
    @SerializedName("runs")
    @Expose
    private String runs;
    @SerializedName("o_summary")
    @Expose
    private List<String> oSummary = null;
    @SerializedName("batsman")
    @Expose
    private List<String> batsman = null;
    @SerializedName("bowler")
    @Expose
    private List<String> bowler = null;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWkts() {
        return wkts;
    }

    public void setWkts(String wkts) {
        this.wkts = wkts;
    }

    public String getONo() {
        return oNo;
    }

    public void setONo(String oNo) {
        this.oNo = oNo;
    }

    public String getIId() {
        return iId;
    }

    public void setIId(String iId) {
        this.iId = iId;
    }

    public String getRuns() {
        return runs;
    }

    public void setRuns(String runs) {
        this.runs = runs;
    }

    public List<String> getOSummary() {
        return oSummary;
    }

    public void setOSummary(List<String> oSummary) {
        this.oSummary = oSummary;
    }

    public List<String> getBatsman() {
        return batsman;
    }

    public void setBatsman(List<String> batsman) {
        this.batsman = batsman;
    }

    public List<String> getBowler() {
        return bowler;
    }

    public void setBowler(List<String> bowler) {
        this.bowler = bowler;
    }

}
