
package com.popseven.livecricketscore.Model.MiniCommentary;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BatTeam {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("innings")
    @Expose
    private List<Inning> innings = null;

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

    public List<Inning> getInnings() {
        return innings;
    }

    public void setInnings(List<Inning> innings) {
        this.innings = innings;
    }

}
