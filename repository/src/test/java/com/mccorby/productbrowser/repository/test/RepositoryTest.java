package com.mccorby.productbrowser.repository.test;

import com.mccorby.productbrowser.domain.entities.Product;
import com.mccorby.productbrowser.domain.repository.ProductRepository;
import com.mccorby.productbrowser.repository.ProductRepositoryImpl;
import com.mccorby.productbrowser.repository.datasources.NetworkDatasource;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by JAC on 03/06/2015.
 */
public class RepositoryTest {

    private List<Product> mExpectedResult;
    private List<Product> mResult;

    @Before
    public void setUp() {
        mExpectedResult = createListOfProducts();
    }

    @Test
    public void testListOfProducts() {
        NetworkDatasource networkDatasource = new NetworkDatasource() {
            @Override
            public List<Product> getProducts() {
                mResult = createListOfProducts();
                return mResult;
            }

            @Override
            public Product getProduct(Integer id) {
                return mResult.get(0);
            }
        };

        ProductRepository repository = new ProductRepositoryImpl(networkDatasource);
        List<Product> result = repository.getProducts();
        assertEquals(mExpectedResult.get(0).getId(), result.get(0).getId());

        Product product = repository.getProduct(1);
        assertEquals(mExpectedResult.get(0).getId(), product.getId());

    }

    private List<Product> createListOfProducts() {
        Product aProduct = new Product();
        aProduct.setImageUrl("1");
        aProduct.setImageUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg");
        aProduct.setName("Apples");
        aProduct.setPrice(120);
        return new ArrayList<>(Collections.nCopies(10, aProduct));
    }
}
