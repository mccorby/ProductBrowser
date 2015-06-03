package com.mccorby.productbrowser.datasource.network.entities;

/**
 * Created by JAC on 03/06/2015.
 */
public class ApiProduct {
    private String mId;
    private String mName;
    private String mImageUrl;
    private int mPrice;

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
}
