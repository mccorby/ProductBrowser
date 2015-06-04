package com.mccorby.productbrowser.app.view.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mccorby.productbrowser.R;
import com.mccorby.productbrowser.app.domain.BusImpl;
import com.mccorby.productbrowser.app.domain.InteractorInvokerImpl;
import com.mccorby.productbrowser.app.presentation.entities.PresentationProduct;
import com.mccorby.productbrowser.app.presentation.productdetail.ProductDetailPresenter;
import com.mccorby.productbrowser.app.presentation.productdetail.ProductDetailView;
import com.mccorby.productbrowser.app.view.Constants;
import com.mccorby.productbrowser.datasource.network.NetworkDatasourceImpl;
import com.mccorby.productbrowser.datasource.network.ProductApiService;
import com.mccorby.productbrowser.domain.abstractions.Bus;
import com.mccorby.productbrowser.domain.interactors.Interactor;
import com.mccorby.productbrowser.domain.interactors.InteractorInvoker;
import com.mccorby.productbrowser.domain.interactors.products.GetProductInteractor;
import com.mccorby.productbrowser.domain.repository.ProductRepository;
import com.mccorby.productbrowser.repository.ProductRepositoryImpl;
import com.mccorby.productbrowser.repository.datasources.NetworkDatasource;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment implements ProductDetailView {

    private static final String TAG = ProductDetailFragment.class.getSimpleName();

    private ProductDetailPresenter mPresenter;
    private String mId;

    // Widgets in the view.
    // I am using Butterknife injection for demonstration purpose
    @InjectView(R.id.fragment_product_detail_name_tv)
    TextView mNameTv;
    @InjectView(R.id.fragment_product_detail_image_iv)
    ImageView mImageView;
    @InjectView(R.id.fragment_product_detail_description_tv)
    TextView mDescriptionTv;
    @InjectView(R.id.fragment_product_detail_price_tv)
    TextView mPriceTv;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // TODO This method to be replaced by proper DI (Dagger2)
        mId = getArguments().getString(Constants.ARG_PRODUCT_ID);
        injectObjects();
    }

    private void injectObjects() {
        ProductApiService apiService = new RestAdapter.Builder()
                .setEndpoint("https://s3-eu-west-1.amazonaws.com/developer-application-test")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(ProductApiService.class);
        NetworkDatasource networkDatasource = new NetworkDatasourceImpl(apiService);
        ProductRepository repository = new ProductRepositoryImpl(networkDatasource);
        Bus bus = new BusImpl(new EventBus());
        Interactor interactor = new GetProductInteractor(bus, repository, mId);
        InteractorInvoker interactorInvoker = new InteractorInvokerImpl(Executors.newSingleThreadExecutor());
        mPresenter = new ProductDetailPresenter(this, bus, interactor, interactorInvoker);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        ButterKnife.inject(this, rootView);
        mPresenter.onCreate();
        return rootView;
    }


    @Override
    public void productAvailable(PresentationProduct presentationProduct) {
        Log.d(TAG, "Product received " + presentationProduct.toString());
        buildUI(presentationProduct);
    }

    private void buildUI(PresentationProduct presentationProduct) {
        mDescriptionTv.setText(presentationProduct.getDescription());
        mNameTv.setText(presentationProduct.getName());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String priceString = formatter.format((float) presentationProduct.getPrice() / 100);
        mPriceTv.setText(priceString);

        Picasso.with(getActivity())
                .load(presentationProduct.getImageUrl())
                .into(mImageView);
    }
}
