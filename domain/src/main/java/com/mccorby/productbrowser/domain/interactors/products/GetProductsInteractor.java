package com.mccorby.productbrowser.domain.interactors.products;

import com.mccorby.productbrowser.domain.abstractions.Bus;
import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.repository.ProductRepository;

import java.util.List;

/**
 * Created by JAC on 03/06/2015.
 */
public class GetProductsInteractor implements Interactor {

    private final ProductRepository mRepository;
    private final Bus mBus;

    public GetProductsInteractor(Bus bus, ProductRepository repository) {
        this.mBus = bus;
        this.mRepository = repository;
    }

    @Override
    public void execute() {
        List<Product> productList = mRepository.getProducts();
        mBus.post(productList);
    }
}
