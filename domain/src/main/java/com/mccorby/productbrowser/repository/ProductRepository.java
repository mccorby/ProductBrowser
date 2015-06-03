package com.mccorby.productbrowser.repository;

import com.mccorby.productbrowser.entities.Product;

import java.util.List;

/**
 * Created by JAC on 03/06/2015.
 */
public interface ProductRepository {

    List<Product> getProducts();
    Product getProduct(Integer identifier);
}
