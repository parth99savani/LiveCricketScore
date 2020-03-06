
package com.popseven.livecricketscore.Model.Series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {

    @SerializedName("match_desc")
    @Expose
    private String matchDesc;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;

    public String getMatchDesc() {
        return matchDesc;
    }

    public void setMatchDesc(String matchDesc) {
        this.matchDesc = matchDesc;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
