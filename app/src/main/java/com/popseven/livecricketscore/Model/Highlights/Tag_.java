
package com.popseven.livecricketscore.Model.Highlights;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("label")
    @Expose
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
