package com.mccorby.productbrowser.datasource.network;

import com.mccorby.productbrowser.datasource.network.entities.ApiProduct;
import com.mccorby.productbrowser.datasource.network.entities.Mapper;
import com.mccorby.productbrowser.datasource.network.responses.ProductsApiResponse;
import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.repository.datasources.NetworkDatasource;

import java.util.Collections;
import java.util.List;

public class NetworkDatasourceImpl implements NetworkDatasource {

    private final ProductApiService mApiService;

    public NetworkDatasourceImpl(ProductApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public List<Product> getProducts() {
        ProductsApiResponse response = mApiService.getProducts();
        if (response.getProductList() != null) {
            return Mapper.transform(response.getProductList());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Product getProduct(String id) {
        ApiProduct result = mApiService.getProduct(String.valueOf(id));
        return Mapper.transform(result);
    }
}
