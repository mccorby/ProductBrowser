package com.mccorby.productbrowser.app.view.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mccorby.productbrowser.R;
import com.mccorby.productbrowser.app.domain.BusImpl;
import com.mccorby.productbrowser.app.domain.InteractorInvokerImpl;
import com.mccorby.productbrowser.app.presentation.entities.PresentationProduct;
import com.mccorby.productbrowser.app.presentation.productlist.ProductListPresenter;
import com.mccorby.productbrowser.app.presentation.productlist.ProductListView;
import com.mccorby.productbrowser.app.view.adapters.ProductListAdapter;
import com.mccorby.productbrowser.datasource.network.NetworkDatasourceImpl;
import com.mccorby.productbrowser.datasource.network.ProductApiService;
import com.mccorby.productbrowser.domain.abstractions.Bus;
import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.interactors.InteractorInvoker;
import com.mccorby.productbrowser.domain.interactors.products.GetProductsInteractor;
import com.mccorby.productbrowser.domain.repository.ProductRepository;
import com.mccorby.productbrowser.repository.ProductRepositoryImpl;
import com.mccorby.productbrowser.repository.datasources.NetworkDatasource;

import java.util.List;
import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

/**
 *
 */
public class ProductListFragment extends Fragment implements ProductListView {

    private static final String TAG = ProductListFragment.class.getSimpleName();
    private static final int GRID_COLUMNS = 2;

    // TODO This member to be injected with @Inject
    ProductListPresenter mPresenter;

    private ProductListAdapter mAdapter;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // TODO This method to be replaced by proper DI (Dagger2)
        injectObjects();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    private void injectObjects() {
        // Manually inject the required objects
        ProductApiService apiService = new RestAdapter.Builder()
                .setEndpoint("https://s3-eu-west-1.amazonaws.com/developer-application-test")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(ProductApiService.class);
        NetworkDatasource networkDatasource = new NetworkDatasourceImpl(apiService);
        ProductRepository repository = new ProductRepositoryImpl(networkDatasource);
        Bus bus = new BusImpl(new EventBus());
        Interactor interactor = new GetProductsInteractor(bus, repository);
        InteractorInvoker interactorInvoker = new InteractorInvokerImpl(Executors.newSingleThreadExecutor());
        mPresenter = new ProductListPresenter(this, bus, interactor, interactorInvoker);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_product_list_rv);
        // Use a linear layout manager
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), GRID_COLUMNS);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ProductListAdapter(getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        // The presenter will retrieve the data from the repository and notify this view
        mPresenter.onCreate();

        return rootView;
    }

    @Override
    public void refreshProductList(List<PresentationProduct> productList) {
        Log.d(TAG, "Refreshing product list in view " + productList.size());
        for (PresentationProduct product : productList) {
            Log.d(TAG, product.toString());
        }
        mAdapter.setProductList(productList);
        mAdapter.notifyDataSetChanged();
    }
}
