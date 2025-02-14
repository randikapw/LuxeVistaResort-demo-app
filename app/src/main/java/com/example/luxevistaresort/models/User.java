package com.example.luxevistaresort.models;

public class User {
    private String name;
    private String email;
    private String contact;
    private String nic;
    private String gender;
    private String dob;
    private byte[] profileImage;

    public User() {
    }

    public User(String name, String email, String contact, String nic, String gender, String dob, byte[] profileImage) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.nic = nic;
        this.gender = gender;
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

    public String getNic() {
        return nic;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}


