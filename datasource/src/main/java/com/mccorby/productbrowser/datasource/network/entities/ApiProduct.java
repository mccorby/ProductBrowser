package com.mccorby.productbrowser.datasource.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JAC on 03/06/2015.
 */
public class ApiProduct {
    @SerializedName("product_id")
    private String mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("image")
    private String mImageUrl;
    @SerializedName("price")
    private int mPrice;
    @SerializedName("description")
    private String mDescription;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
