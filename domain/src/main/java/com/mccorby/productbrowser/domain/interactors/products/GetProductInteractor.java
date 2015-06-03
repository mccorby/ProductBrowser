package com.mccorby.productbrowser.domain.interactors.products;

import com.mccorby.productbrowser.domain.abstractions.Bus;
import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.repository.ProductRepository;

/**
 * Created by JAC on 03/06/2015.
 */
public class GetProductInteractor implements Interactor {

    private final ProductRepository mRepository;
    private final Bus mBus;
    private final String mId;

    public GetProductInteractor(Bus bus, ProductRepository repository, String identifier) {
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
