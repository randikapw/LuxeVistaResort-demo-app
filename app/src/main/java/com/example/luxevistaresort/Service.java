package com.example.luxevistaresort;

public class Service {
    private int S_ID;
    private String S_Name;
    private String Category;
    private double Price;
    private boolean Availability;

    public Service(int s_ID, String s_Name, String category, double price, boolean availability) {
        this.S_ID = s_ID;
        this.S_Name = s_Name;
        this.Category = category;
        this.Price = price;
        this.Availability = availability;
    }

    public int getS_ID() {
        return S_ID;
    }

    public void setS_ID(int s_ID) {
        S_ID = s_ID;
    }

    public String getS_Name() {
        return S_Name;
    }

    public void setS_Name(String s_Name) {
        S_Name = s_Name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }
}
