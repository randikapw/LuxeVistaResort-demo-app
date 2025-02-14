package com.example.luxevistaresort.models;

public class User {
    private String name;
    private String email;
    private String contact;
    private String dob;
    private byte[] profileImage;

    public User(String name, String email, String contact, String dob, byte[] profileImage) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.dob = dob;
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getDob() {
        return dob;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }
}


