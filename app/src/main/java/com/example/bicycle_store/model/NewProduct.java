package com.example.bicycle_store.model;

public class NewProduct {
    private  String name;
    private String price;
    private String category;
    private String image_url;
    private String description;

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

    public NewProduct(String name, String price, String category, String image_url, String description , String id) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.image_url = image_url;
        this.description = description;
        this.id = id;
    }
}
