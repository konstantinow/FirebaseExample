package com.besplatka.test;

import android.os.Parcelable;

import java.io.Serializable;

class Poster implements Serializable {
    private String id;
    private String title;
    private String description;
    private String name;
    private String phone;
    private String city;
    private int cost = 0;

    public Poster(String id, String title, String description, String name, String phone, String city, int cost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
