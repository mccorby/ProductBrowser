package com.mccorby.productbrowser.domain.test;

import com.mccorby.productbrowser.domain.abstractions.Bus;
import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.interactors.InteractorInvoker;
import com.mccorby.productbrowser.domain.interactors.products.GetProductsInteractor;
import com.mccorby.productbrowser.domain.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by JAC on 03/06/2015.
 */
public class InteractorsTest {

    private Bus mBus;
    private ProductRepository mRepository;
    private InteractorInvoker mInvoker;
    private List<Product> mExpectedResult;
    private List<Product> mResult;

    @Before
    public void setUp() {
        mBus = new Bus() {
            @Override
            public void post(Object object) {
                mResult = (List<Product>) object;
            }

            @Override
            public void register(Object object) {

            }

            @Override
            public void unregister(Object object) {

            }
        };
        mRepository = new ProductRepository() {
            @Override
            public List<Product> getProducts() {
                Product aProduct = new Product();
                aProduct.setImageUrl("1");
                aProduct.setImageUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg");
                aProduct.setName("Apples");
                aProduct.setPrice(120);
                mResult = new ArrayList<>(Collections.nCopies(10, aProduct));
                return mResult;
            }

            @Override
            public Product getProduct(Integer identifier) {
                return null;
            }
        };
        mInvoker = new InteractorInvoker() {
            @Override
            public void execute(Interactor interactor) {
                interactor.execute();
            }
        };

        Product aProduct = new Product();
        aProduct.setImageUrl("1");
        aProduct.setImageUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg");
        aProduct.setName("Apples");
        aProduct.setPrice(120);
        mExpectedResult = new ArrayList<>(Collections.nCopies(10, aProduct));
    }

    @Test
    public void getProducts() {
        Interactor interactor = new GetProductsInteractor(mBus, mRepository);
        mInvoker.execute(interactor);
        assertEquals(mExpectedResult.get(0).getId(), mResult.get(0).getId());
    }
}
