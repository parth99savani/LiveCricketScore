
package com.popseven.livecricketscore.Model.PointTableList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointTableList {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("series")
    @Expose
    private List<Series> series = null;
    @SerializedName("tabs")
    @Expose
    private List<Tab> tabs = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

}
