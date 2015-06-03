package com.mccorby.productbrowser.app.presentation.productlist;

import com.mccorby.productbrowser.app.presentation.Presenter;
import com.mccorby.productbrowser.app.presentation.entities.Mapper;
import com.mccorby.productbrowser.app.presentation.entities.PresentationProduct;
import com.mccorby.productbrowser.domain.abstractions.Bus;
import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.interactors.InteractorInvoker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAC on 03/06/2015.
 */
public class ProductListPresenter implements Presenter {

    private final ProductListView mProductListView;
    private Bus mBus;
    private Interactor mInteractor;
    private InteractorInvoker mInvoker;

    public ProductListPresenter(ProductListView view, Bus bus, Interactor interactor, InteractorInvoker interactorInvoker) {
        this.mProductListView = view;
        this.mBus = bus;
        this.mInteractor = interactor;
        this.mInvoker = interactorInvoker;
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

    /**
     * Required by EventBus to be named as "onEvent".
     *
     * @param productList
     */
    public void onEvent(List<Product> productList) {
        List<PresentationProduct> presentationProducts = new ArrayList<>(productList.size());
        for (Product aProduct : productList) {
            presentationProducts.add(Mapper.transform(aProduct));
        }
        mProductListView.refreshProductList(presentationProducts);
    }
}
