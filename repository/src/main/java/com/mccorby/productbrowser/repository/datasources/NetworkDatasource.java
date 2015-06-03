package com.mccorby.productbrowser.repository.datasources;

import com.mccorby.productbrowser.domain.entities.Product;

import java.util.List;

/**
 * Created by JAC on 03/06/2015.
 */
public interface NetworkDatasource {
    List<Product> getProducts();
    Product getProduct(String id);
}
