
package com.popseven.livecricketscore.Model.PointTable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("Teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("Group D")
    @Expose
    private List<GroupD> groupD = null;
    @SerializedName("Group C")
    @Expose
    private List<GroupC> groupC = null;
    @SerializedName("Group B")
    @Expose
    private List<GroupB> groupB = null;
    @SerializedName("Group A")
    @Expose
    private List<GroupA> groupA = null;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<GroupD> getGroupD() {
        return groupD;
    }

    public void setGroupD(List<GroupD> groupD) {
        this.groupD = groupD;
    }

    public List<GroupC> getGroupC() {
        return groupC;
    }

    public void setGroupC(List<GroupC> groupC) {
        this.groupC = groupC;
    }

    public List<GroupB> getGroupB() {
        return groupB;
    }

    public void setGroupB(List<GroupB> groupB) {
        this.groupB = groupB;
    }

    public List<GroupA> getGroupA() {
        return groupA;
    }

    public void setGroupA(List<GroupA> groupA) {
        this.groupA = groupA;
    }


}
