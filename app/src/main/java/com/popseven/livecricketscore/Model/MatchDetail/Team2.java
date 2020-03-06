
package com.popseven.livecricketscore.Model.MatchDetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team2 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("s_name")
    @Expose
    private String sName;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("squad")
    @Expose
    private List<Integer> squad = null;
    @SerializedName("squad_bench")
    @Expose
    private List<Integer> squadBench = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Integer> getSquad() {
        return squad;
    }

    public void setSquad(List<Integer> squad) {
        this.squad = squad;
    }

    public List<Integer> getSquadBench() {
        return squadBench;
    }

    public void setSquadBench(List<Integer> squadBench) {
        this.squadBench = squadBench;
    }

}
