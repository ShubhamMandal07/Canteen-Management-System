package com.example.canteenm;

public class Data {
    String name, image, date, time, key, price, foodtype;


    public Data(String name, String image, String date, String time, String key, String price, String foodtype) {
        this.name = name;
        this.image = image;
        this.date = date;
        this.time = time;
        this.key = key;
        this.price = price;
        this.foodtype = foodtype;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getfoodtype() {
        return foodtype;
    }

    public void setfoodtype(String foodtype) {
        this.foodtype = foodtype;
    }
}


