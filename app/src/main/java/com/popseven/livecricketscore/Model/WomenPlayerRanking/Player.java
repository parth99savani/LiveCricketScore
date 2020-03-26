
package com.popseven.livecricketscore.Model.WomenPlayerRanking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("rightArmedBowl")
    @Expose
    private Boolean rightArmedBowl;
    @SerializedName("rightHandedBat")
    @Expose
    private Boolean rightHandedBat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Boolean getRightArmedBowl() {
        return rightArmedBowl;
    }

    public void setRightArmedBowl(Boolean rightArmedBowl) {
        this.rightArmedBowl = rightArmedBowl;
    }

    public Boolean getRightHandedBat() {
        return rightHandedBat;
    }

    public void setRightHandedBat(Boolean rightHandedBat) {
        this.rightHandedBat = rightHandedBat;
    }

}
