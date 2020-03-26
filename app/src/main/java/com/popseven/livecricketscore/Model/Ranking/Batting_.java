
package com.popseven.livecricketscore.Model.Ranking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batting_ {

    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("avg")
    @Expose
    private String avg;
    @SerializedName("img")
    @Expose
    private String img;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
