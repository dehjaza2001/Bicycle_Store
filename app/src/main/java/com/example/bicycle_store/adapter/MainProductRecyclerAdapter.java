package com.example.bicycle_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bicycle_store.R;
import com.example.bicycle_store.model.MainProduct;
//import com.example.bicycle_store.model.NewProduct;

import java.util.List;

public class MainProductRecyclerAdapter extends RecyclerView.Adapter<MainProductRecyclerAdapter.MainProductViewHolder> {
    private Context context;
    private List<MainProduct> mainProductList;

    public MainProductRecyclerAdapter(Context context, List<MainProduct> mainProductList) {
        this.context = context;
        this.mainProductList = mainProductList;
    }


    @Override
    public MainProductRecyclerAdapter.MainProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.main_product_recycler_items,parent,false);
        final MainProductRecyclerAdapter.MainProductViewHolder viewHolder = new MainProductRecyclerAdapter.MainProductViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MainProductRecyclerAdapter.MainProductViewHolder mainProductViewHolder, int i) {
        mainProductViewHolder.BicName.setText(mainProductList.get(i).getName());
        mainProductViewHolder.price.setText(mainProductList.get(i).getPrice());
        mainProductViewHolder.category.setText(mainProductList.get(i).getCategory());
        Glide.with(context).load(mainProductList.get(i).getImage_url()).into(mainProductViewHolder.itemImage);
    }

    @Override
    public int getItemCount() {
        return mainProductList.size();
    }
    public static class MainProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView itemImage;
        TextView price, BicName , category;
        LinearLayout linearLayout_main_product;

        public MainProductViewHolder(View itemView) {
            super(itemView);
            linearLayout_main_product = itemView.findViewById(R.id.main_product);
            itemImage = itemView.findViewById(R.id.bicImageView);
            price = itemView.findViewById(R.id.price);
            BicName = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.category);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
