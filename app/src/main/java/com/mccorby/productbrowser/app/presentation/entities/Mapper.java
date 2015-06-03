package com.mccorby.productbrowser.app.presentation.entities;

import com.mccorby.productbrowser.domain.entities.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAC on 03/06/2015.
 */
public class Mapper {

    public static Product transform(PresentationProduct presentationProduct) {
        Product product = new Product();
        product.setDescription(presentationProduct.getDescription());
        product.setImageUrl(presentationProduct.getImageUrl());
        product.setPrice(presentationProduct.getPrice());
        product.setName(presentationProduct.getName());
        product.setId(presentationProduct.getId());

        return product;
    }

    public static List<Product> transform(List<PresentationProduct> presentationProductList) {
        List<Product> result = new ArrayList<>(presentationProductList.size());

        for (PresentationProduct presentationProduct : presentationProductList) {
            result.add(Mapper.transform(presentationProduct));
        }

        return result;
    }
}
