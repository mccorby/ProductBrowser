package com.mccorby.productbrowser.datasource.network.entities;

import com.mccorby.productbrowser.domain.entities.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class has static methods to transform models in this layer into the domain models
 * and viceversa (if needed).
 *
 * Created by JAC on 03/06/2015.
 */
public class Mapper {

    public static List<Product> transform(List<ApiProduct> apiProductList) {
        List<Product> domainList = new ArrayList<>(apiProductList.size());
        for (ApiProduct apiProduct : apiProductList) {
            domainList.add(Mapper.transform(apiProduct));
        }
        return domainList;
    }

    public static Product transform(ApiProduct apiProduct) {
        Product domainProduct = new Product();
        domainProduct.setImageUrl(apiProduct.getImageUrl());
        domainProduct.setName(apiProduct.getName());
        domainProduct.setImageUrl(apiProduct.getId());
        domainProduct.setPrice(apiProduct.getPrice());

        return domainProduct;
    }
}
