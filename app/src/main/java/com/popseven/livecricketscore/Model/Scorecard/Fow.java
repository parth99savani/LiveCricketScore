
package com.popseven.livecricketscore.Model.Scorecard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fow {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("wkt_nbr")
    @Expose
    private String wktNbr;
    @SerializedName("over")
    @Expose
    private String over;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWktNbr() {
        return wktNbr;
    }

    public void setWktNbr(String wktNbr) {
        this.wktNbr = wktNbr;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

}
