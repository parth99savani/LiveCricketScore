
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
    @SerializedName("Elite Group A and B")
    @Expose
    private List<EliteGroupAAndB> eliteGroupAAndB = null;
    @SerializedName("Elite Group C")
    @Expose
    private List<EliteGroupC> eliteGroupC = null;
    @SerializedName("Plate")
    @Expose
    private List<Plate> plate = null;

    public List<EliteGroupAAndB> getEliteGroupAAndB() {
        return eliteGroupAAndB;
    }

    public void setEliteGroupAAndB(List<EliteGroupAAndB> eliteGroupAAndB) {
        this.eliteGroupAAndB = eliteGroupAAndB;
    }

    public List<EliteGroupC> getEliteGroupC() {
        return eliteGroupC;
    }

    public void setEliteGroupC(List<EliteGroupC> eliteGroupC) {
        this.eliteGroupC = eliteGroupC;
    }

    public List<Plate> getPlate() {
        return plate;
    }

    public void setPlate(List<Plate> plate) {
        this.plate = plate;
    }

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
