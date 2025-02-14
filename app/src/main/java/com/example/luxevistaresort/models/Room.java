package com.example.luxevistaresort.models;

public class Room {
    private int id;
    private String type;
    private String description;
    private double price;
    private int capacity;
    private int imageResId;  // Resource ID for room image
    private boolean isAvailable;
    private String amenities;
    private String viewType;  // e.g., "Ocean View", "Garden View"
    private int bedCount;

    public Room(int id, String type, String description, double price, int capacity, 
               int imageResId, boolean isAvailable, String amenities, String viewType, int bedCount) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.imageResId = imageResId;
        this.isAvailable = isAvailable;
        this.amenities = amenities;
        this.viewType = viewType;
        this.bedCount = bedCount;
    }

    // Getters
    public int getId() { return id; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getCapacity() { return capacity; }
    public int getImageResId() { return imageResId; }
    public boolean isAvailable() { return isAvailable; }
    public String getAmenities() { return amenities; }
    public String getViewType() { return viewType; }
    public int getBedCount() { return bedCount; }
} 