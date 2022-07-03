package com.example.bicycle_store.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Parcelable
{
    List<MainProduct> list_product;

    public Cart(Parcel in) {
        list_product = in.createTypedArrayList(MainProduct.CREATOR);
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(list_product);
    }

    public Cart() {
        this.list_product = new ArrayList<>();
    }

    public List<MainProduct> getListProduct() {
        return list_product;
    }

    public void setListProduct(List<MainProduct> list_product) {
        this.list_product = list_product;
    }

    public int getTotal() {
        int total = 0;
        for (int i = 0; i < list_product.size(); ++i) {
            total += Integer.parseInt(list_product.get(i).getPrice());
        }
        return total;
    }

    public boolean addProduct(MainProduct product) {
        return this.list_product.add(product);
    }

    public boolean removeProduct(int index) {
        if (index >= this.list_product.size()) return false;
        this.list_product.remove(index);
        return true;
    }
}
