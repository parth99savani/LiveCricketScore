
package com.popseven.livecricketscore.Model.Series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team1 {

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

}
