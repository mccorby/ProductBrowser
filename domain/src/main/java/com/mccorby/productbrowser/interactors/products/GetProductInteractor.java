package com.mccorby.productbrowser.interactors.products;

import com.mccorby.productbrowser.abstractions.Bus;
import com.mccorby.productbrowser.entities.Product;
import com.mccorby.productbrowser.interactors.Interactor;
import com.mccorby.productbrowser.repository.ProductRepository;

/**
 * Created by JAC on 03/06/2015.
 */
public class GetProductInteractor implements Interactor {

    private final ProductRepository mRepository;
    private final Bus mBus;
    private final Integer mId;

    public GetProductInteractor(Bus bus, ProductRepository repository, Integer identifier) {
        this.mBus = bus;
        this.mRepository = repository;
        this.mId = identifier;
    }


    @Override
    public void execute() {
        Product product = mRepository.getProduct(mId);
        mBus.post(product);
    }
}
