package com.mccorby.productbrowser.app.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mccorby.productbrowser.R;
import com.mccorby.productbrowser.app.presentation.entities.PresentationProduct;
import com.mccorby.productbrowser.app.view.transformation.ThumbnailTransformation;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

/**
 *
 * Created by JAC on 03/06/2015.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private List<PresentationProduct> mProductList;
    private OnProductClickListener mListener;

    public interface OnProductClickListener {
        void onProductSelected(PresentationProduct product);
    }

    public ProductListAdapter(Context context, OnProductClickListener listener) {
        this.mContext = context;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_list, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        PresentationProduct product = mProductList.get(i);
        viewHolder.mName.setText(product.getName());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String priceString = formatter.format((float) product.getPrice() / 100);

        viewHolder.mPrice.setText(priceString);
        Picasso.with(mContext)
                .load(product.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_error)
                .transform(new ThumbnailTransformation())
                .into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mProductList != null ? mProductList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mName;
        TextView mPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_product_list_image_iv);
            mName = (TextView) itemView.findViewById(R.id.item_product_list_name_tv);
            mPrice = (TextView) itemView.findViewById(R.id.item_product_list_price_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onProductSelected(mProductList.get(getPosition()));

                }
            });
        }
    }

    public void setProductList(List<PresentationProduct> productList) {
        this.mProductList = productList;
    }
}
