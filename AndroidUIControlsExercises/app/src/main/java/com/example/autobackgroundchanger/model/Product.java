package com.example.autobackgroundchanger.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("productId")
    private int id;
    
    @SerializedName("productName")
    private String name;
    
    @SerializedName("image")
    private String image;
    
    @SerializedName("price")
    private double price;
    
    @SerializedName("description")
    private String description;

    public Product(int id, String name, String image, double price, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
