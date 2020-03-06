
package com.popseven.livecricketscore.Model.MiniCommentary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("match_desc")
    @Expose
    private String matchDesc;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("state_title")
    @Expose
    private String stateTitle;
    @SerializedName("toss")
    @Expose
    private String toss;
    @SerializedName("status")
    @Expose
    private String status;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMatchDesc() {
        return matchDesc;
    }

    public void setMatchDesc(String matchDesc) {
        this.matchDesc = matchDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStateTitle() {
        return stateTitle;
    }

    public void setStateTitle(String stateTitle) {
        this.stateTitle = stateTitle;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
