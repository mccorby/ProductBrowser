package com.mccorby.productbrowser.app.view.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.mccorby.productbrowser.R;
import com.mccorby.productbrowser.app.view.Constants;
import com.mccorby.productbrowser.app.view.fragments.ProductDetailFragment;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        Toolbar toolbar = (Toolbar) findViewById(R.id.action_toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState == null) {
            Fragment fragment = new ProductDetailFragment();
            Bundle args = new Bundle();
            // The intent should contain an extra with the id of the product
            if (getIntent().getExtras() != null
                    && !TextUtils.isEmpty(getIntent().getStringExtra(Constants.ARG_PRODUCT_ID))) {
                args.putString(Constants.ARG_PRODUCT_ID, getIntent().getStringExtra(Constants.ARG_PRODUCT_ID));
            }
            fragment.setArguments(args);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }
}
