package com.example.luxevistaresort.views.room;

public class Room {
  private int Room_ID;
  private String Room_name;
  private String Room_Type;
  private double price;
  private boolean availability;

    public Room(int room_ID, String room_name, String room_Type, double price, boolean availability) {
        this.Room_ID = room_ID;
        this.Room_name = room_name;
        this.Room_Type = room_Type;
        this.price = price;
        this.availability = availability;
    }

    public int getRoom_ID() {
        return Room_ID;
    }

    public void setRoom_ID(int room_ID) {
        Room_ID = room_ID;
    }

    public String getRoom_name() {
        return Room_name;
    }

    public void setRoom_name(String room_name) {
        Room_name = room_name;
    }

    public String getRoom_Type() {
        return Room_Type;
    }

    public void setRoom_Type(String room_Type) {
        Room_Type = room_Type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
