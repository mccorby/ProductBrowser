package com.mccorby.productbrowser.datasource.network;

import com.mccorby.productbrowser.datasource.network.entities.ApiProduct;
import com.mccorby.productbrowser.datasource.network.responses.ProductsApiResponse;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * This interface defines the operations available in the API (and used in this project, of course).
 * It is annotated with Retrofit annotations as this is the selected network library.
 * Created by JAC on 03/06/2015.
 */
public interface ProductApiService {
    @GET("/cart/list")
    ProductsApiResponse getProducts();

    @GET("/cart/{product_id}/detail")
    ApiProduct getProduct(@Path("product_id") String productId);

}
