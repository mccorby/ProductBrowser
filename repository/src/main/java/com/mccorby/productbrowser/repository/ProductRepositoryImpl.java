package com.mccorby.productbrowser.repository;

import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.domain.repository.ProductRepository;
import com.mccorby.productbrowser.repository.datasources.NetworkDatasource;

import java.util.List;

/**
 * An implementation of a product repository.
 * In this case it uses a network datasource to retrieve the data.
 * It uses a local list to temporarily store the products. It could be seen as a very
 * basic cache that will exist while this repository object exists.
 * A better solution would be to have
 * a local datasource holding this list (it could then be in memory or in database, for instance).
 */
public class ProductRepositoryImpl implements ProductRepository{

    private NetworkDatasource mNetworkDatasource;
    private List<Product> mProductList;

    public ProductRepositoryImpl(NetworkDatasource networkDatasource) {
        mNetworkDatasource = networkDatasource;
    }

    @Override
    public List<Product> getProducts() {
        if (mProductList == null) {
            mProductList = mNetworkDatasource.getProducts();
        }
        return mProductList;
    }

    @Override
    public Product getProduct(String identifier) {
        return mNetworkDatasource.getProduct(identifier);
    }
}
