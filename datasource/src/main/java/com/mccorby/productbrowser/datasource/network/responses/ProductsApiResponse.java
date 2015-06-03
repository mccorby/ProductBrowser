package com.mccorby.productbrowser.datasource.network.responses;

import com.google.gson.annotations.SerializedName;
import com.mccorby.productbrowser.datasource.network.entities.ApiProduct;

import java.util.List;

/**
 * Wrapper of the result returned by the API for a list of products.
 * Created by JAC on 03/06/2015.
 */
public class ProductsApiResponse {

    @SerializedName("products")
    private List<ApiProduct> mProductList;

    public List<ApiProduct> getProductList() {
        return mProductList;
    }

    public void setProductList(List<ApiProduct> productList) {
        mProductList = productList;
    }
}
