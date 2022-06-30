package com.example.bicycle_store.adapter;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
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
import com.example.bicycle_store.model.NewProduct;

import java.util.List;

public class NewProductRecyclerAdapter extends RecyclerView.Adapter<NewProductRecyclerAdapter.NewProductViewHolder> {
    private Context context;

    public NewProductRecyclerAdapter(Context context, List<NewProduct> newProductList) {
        this.context = context;
        this.newProductList = newProductList;
    }

    private List<NewProduct> newProductList;
    @Override
    public NewProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.new_product_recycler_items,parent,false);
        final NewProductViewHolder viewHolder = new NewProductViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NewProductRecyclerAdapter.NewProductViewHolder newProductViewHolder, int i) {
        newProductViewHolder.BicName.setText(newProductList.get(i).getName());
        newProductViewHolder.price.setText(newProductList.get(i).getPrice());
        Glide.with(context).load(newProductList.get(i).getImage_url()).into(newProductViewHolder.itemImage);
    }

    @Override
    public int getItemCount() {
        return newProductList.size();
    }
    public static class NewProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView itemImage;
        TextView price, BicName;
        LinearLayout linearLayout_new_product;

        public NewProductViewHolder(View itemView) {
            super(itemView);
            linearLayout_new_product = itemView.findViewById(R.id.newProduct);
            itemImage = itemView.findViewById(R.id.imageView);
            price = itemView.findViewById(R.id.Price);
            BicName = itemView.findViewById(R.id.BicName);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
