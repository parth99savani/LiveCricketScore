package com.popseven.livecricketscore.Model.MatchDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Official {

    @SerializedName("umpire1")
    @Expose
    private Umpire1 umpire1;
    @SerializedName("umpire2")
    @Expose
    private Umpire2 umpire2;
    @SerializedName("umpire3")
    @Expose
    private Umpire3 umpire3;
    @SerializedName("referee")
    @Expose
    private Referee referee;

    public Umpire1 getUmpire1() {
        return umpire1;
    }

    public void setUmpire1(Umpire1 umpire1) {
        this.umpire1 = umpire1;
    }

    public Umpire2 getUmpire2() {
        return umpire2;
    }

    public void setUmpire2(Umpire2 umpire2) {
        this.umpire2 = umpire2;
    }

    public Umpire3 getUmpire3() {
        return umpire3;
    }

    public void setUmpire3(Umpire3 umpire3) {
        this.umpire3 = umpire3;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

}
