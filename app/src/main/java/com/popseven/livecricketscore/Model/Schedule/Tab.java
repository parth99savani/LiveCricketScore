package com.popseven.livecricketscore.Model.Schedule;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tab {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("page_title")
    @Expose
    private String pageTitle;
    @SerializedName("filters")
    @Expose
    private List<Integer> filters = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public List<Integer> getFilters() {
        return filters;
    }

    public void setFilters(List<Integer> filters) {
        this.filters = filters;
    }

}
