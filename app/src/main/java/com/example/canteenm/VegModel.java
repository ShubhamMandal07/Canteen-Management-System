package com.example.canteenm;

import java.io.Serializable;

public class VegModel implements Serializable {

    String name, image, foodtype, price;
    public  VegModel(){

    }

    public VegModel(String name, String image, String foodtype, String price) {
        this.name = name;
        this.image = image;
        this.foodtype = foodtype;
        this.price = price;
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

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
