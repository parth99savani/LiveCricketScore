
package com.popseven.livecricketscore.Model.MatchDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("f_name")
    @Expose
    private String fName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bat_style")
    @Expose
    private String batStyle;
    @SerializedName("bowl_style")
    @Expose
    private String bowlStyle;
    @SerializedName("speciality")
    @Expose
    private String speciality;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("role")
    @Expose
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatStyle() {
        return batStyle;
    }

    public void setBatStyle(String batStyle) {
        this.batStyle = batStyle;
    }

    public String getBowlStyle() {
        return bowlStyle;
    }

    public void setBowlStyle(String bowlStyle) {
        this.bowlStyle = bowlStyle;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
