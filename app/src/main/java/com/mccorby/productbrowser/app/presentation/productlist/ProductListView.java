package com.mccorby.productbrowser.app.presentation.productlist;

import com.mccorby.productbrowser.app.presentation.entities.PresentationProduct;

import java.util.List;

/**
 * Created by JAC on 03/06/2015.
 */
public interface ProductListView {

    void refreshProductList(List<PresentationProduct> productList);
}
