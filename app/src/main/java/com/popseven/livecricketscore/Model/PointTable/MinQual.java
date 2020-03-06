
package com.popseven.livecricketscore.Model.PointTable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MinQual {

    @SerializedName("Teams")
    @Expose
    private String teams;
    @SerializedName("Group D")
    @Expose
    private String groupD;
    @SerializedName("Group C")
    @Expose
    private String groupC;
    @SerializedName("Group B")
    @Expose
    private String groupB;
    @SerializedName("Group A")
    @Expose
    private String groupA;

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getGroupD() {
        return groupD;
    }

    public void setGroupD(String groupD) {
        this.groupD = groupD;
    }

    public String getGroupC() {
        return groupC;
    }

    public void setGroupC(String groupC) {
        this.groupC = groupC;
    }

    public String getGroupB() {
        return groupB;
    }

    public void setGroupB(String groupB) {
        this.groupB = groupB;
    }

    public String getGroupA() {
        return groupA;
    }

    public void setGroupA(String groupA) {
        this.groupA = groupA;
    }


}
