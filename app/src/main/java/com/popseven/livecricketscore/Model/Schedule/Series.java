package com.popseven.livecricketscore.Model.Schedule;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tabs")
    @Expose
    private List<Integer> tabs = null;

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

    public List<Integer> getTabs() {
        return tabs;
    }

    public void setTabs(List<Integer> tabs) {
        this.tabs = tabs;
    }

}
