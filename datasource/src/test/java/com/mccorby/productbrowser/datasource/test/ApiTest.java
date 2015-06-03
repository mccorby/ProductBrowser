package com.mccorby.productbrowser.datasource.test;

import com.mccorby.productbrowser.datasource.network.NetworkDatasourceImpl;
import com.mccorby.productbrowser.datasource.network.ProductApiService;
import com.mccorby.productbrowser.datasource.network.entities.ApiProduct;
import com.mccorby.productbrowser.datasource.network.entities.Mapper;
import com.mccorby.productbrowser.datasource.network.responses.ProductsApiResponse;
import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.repository.datasources.NetworkDatasource;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.http.Path;

import static org.junit.Assert.assertEquals;

/**
 * Created by JAC on 03/06/2015.
 */
public class ApiTest {

    private List<ApiProduct> mExpectedResult;

    @Before
    public void setUp() {
        mExpectedResult = createListOfApiProducts();
    }

    @Test
    public void testRetrieveProductList() {
        ProductApiService apiService = new ProductApiService() {
            @Override
            public ProductsApiResponse getProducts() {
                ProductsApiResponse response = new ProductsApiResponse();
                response.setProductList(createListOfApiProducts());
                return response;
            }

            @Override
            public ApiProduct getProduct(@Path("product_id") String productId) {
                ApiProduct aProduct = new ApiProduct();
                aProduct.setImageUrl("1");
                aProduct.setImageUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg");
                aProduct.setName("Apples");
                aProduct.setPrice(120);
                return aProduct;
            }
        };
        NetworkDatasource networkDatasource = new NetworkDatasourceImpl(apiService);
        List<Product> result = networkDatasource.getProducts();
        assertEquals(Mapper.transform(mExpectedResult.get(0)).getId(), result.get(0).getId());
    }

    private List<ApiProduct> createListOfApiProducts() {
        ApiProduct aProduct = new ApiProduct();
        aProduct.setImageUrl("1");
        aProduct.setImageUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg");
        aProduct.setName("Apples");
        aProduct.setPrice(120);
        return new ArrayList<>(Collections.nCopies(10, aProduct));
    }
}
