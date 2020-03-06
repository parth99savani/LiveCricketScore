
package com.popseven.livecricketscore.Model.LiveMatches;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BowTeam {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("innings")
    @Expose
    private List<Inning> innings = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Inning> getInnings() {
        return innings;
    }

    public void setInnings(List<Inning> innings) {
        this.innings = innings;
    }

}
