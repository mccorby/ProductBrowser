package com.mccorby.productbrowser.app.presentation.productdetail;

import com.mccorby.productbrowser.app.presentation.Presenter;
import com.mccorby.productbrowser.app.presentation.entities.Mapper;
import com.mccorby.productbrowser.domain.abstractions.Bus;
import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.interactors.InteractorInvoker;

/**
 * Created by JAC on 03/06/2015.
 */
public class ProductDetailPresenter implements Presenter {

    private final ProductDetailView mProductDetailView;
    private final Bus mBus;
    private final Interactor mInteractor;
    private final InteractorInvoker mInvoker;

    public ProductDetailPresenter(ProductDetailView view, Bus bus,
                                  Interactor interactor, InteractorInvoker invoker) {
        this.mProductDetailView = view;
        this.mBus = bus;
        this.mInteractor = interactor;
        this.mInvoker = invoker;
    }

    @Override
    public void onCreate() {
        mInvoker.execute(mInteractor);
    }

    @Override
    public void onResume() {
        mBus.register(this);
    }

    @Override
    public void onPause() {
        mBus.unregister(this);
    }

    public void onEvent(Product product) {
        mProductDetailView.productAvailable(Mapper.transform(product));
    }


}
