package com.example.bicycle_store.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MainProduct implements Parcelable {
    private  String name;
    private String price;
    private String category;

    protected MainProduct(Parcel in) {
        name = in.readString();
        price = in.readString();
        category = in.readString();
        id = in.readString();
        image_url = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(category);
        dest.writeString(id);
        dest.writeString(image_url);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainProduct> CREATOR = new Creator<MainProduct>() {
        @Override
        public MainProduct createFromParcel(Parcel in) {
            return new MainProduct(in);
        }

        @Override
        public MainProduct[] newArray(int size) {
            return new MainProduct[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String image_url;
    private String description;

    public MainProduct(String name, String price, String category, String image_url, String description , String id) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.image_url = image_url;
        this.description = description;
        this.id = id;
    }
}
