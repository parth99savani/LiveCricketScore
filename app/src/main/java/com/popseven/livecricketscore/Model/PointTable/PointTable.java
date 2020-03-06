
package com.popseven.livecricketscore.Model.PointTable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointTable {

    @SerializedName("series_id")
    @Expose
    private String seriesId;
    @SerializedName("series_name")
    @Expose
    private String seriesName;
    @SerializedName("min_qual")
    @Expose
    private MinQual minQual;
    @SerializedName("order")
    @Expose
    private List<String> order = null;
    @SerializedName("title")
    @Expose
    private List<String> title = null;
    @SerializedName("header")
    @Expose
    private List<String> header = null;
    @SerializedName("consider_group")
    @Expose
    private Boolean considerGroup;
    @SerializedName("group")
    @Expose
    private Group group;

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public MinQual getMinQual() {
        return minQual;
    }

    public void setMinQual(MinQual minQual) {
        this.minQual = minQual;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public Boolean getConsiderGroup() {
        return considerGroup;
    }

    public void setConsiderGroup(Boolean considerGroup) {
        this.considerGroup = considerGroup;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
