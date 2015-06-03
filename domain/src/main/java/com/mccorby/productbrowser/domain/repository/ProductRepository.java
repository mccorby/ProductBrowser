package com.mccorby.productbrowser.domain.repository;

import com.mccorby.productbrowser.domain.entities.Product;

import java.util.List;

/**
 * Created by JAC on 03/06/2015.
 */
public interface ProductRepository {

    List<Product> getProducts();
    Product getProduct(String identifier);
}
