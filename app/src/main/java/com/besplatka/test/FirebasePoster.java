package com.besplatka.test;

class FirebasePoster {
    private String title;
    private String description;
    private String name;
    private String phone;
    private String city;
    private int cost;

    public FirebasePoster() {
    }

    public FirebasePoster(String title, String description, String name, String phone, String city, int cost) {
        this.title = title;
        this.description = description;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.cost = cost;
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
