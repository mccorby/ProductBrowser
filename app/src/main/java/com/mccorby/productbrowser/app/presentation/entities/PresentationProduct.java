package com.mccorby.productbrowser.app.presentation.entities;

/**
 * Created by JAC on 03/06/2015.
 */
public class PresentationProduct {

    private String mId;
    private String mName;
    private String mImageUrl;
    private int mPrice;
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

    @Override
    public String toString() {
        return "PresentationProduct{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mPrice=" + mPrice +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
