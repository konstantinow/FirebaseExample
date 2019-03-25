package com.besplatka.test;

class Poster {
    private String id;
    private String title;
    private String description;
    private String name;
    private String phone;
    private double cost;

    public Poster(String id, String title, String description, String name, String phone, double cost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.name = name;
        this.phone = phone;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
