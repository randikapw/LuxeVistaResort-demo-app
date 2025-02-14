package com.example.luxevistaresort.models;

import java.time.LocalDate;


public class Guest {
    private int NIC;
    private String Name;
    private String Email;
    private int Phone_No;
    private LocalDate Date_Of_Birth;
    private String Gender;
    private int Password;

    public Guest(int NIC, String name, String email, int phone_No, LocalDate date_Of_Birth, String gender, int password) {
        this.NIC = NIC;
        this.Name = name;
        this.Email = email;
        this.Phone_No = phone_No;
        this.Date_Of_Birth = date_Of_Birth;
        this.Gender = gender;
        this.Password = password;
    }

    public int getNIC() {
        return NIC;
    }

    public void setNIC(int NIC) {
        this.NIC = NIC;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(int phone_No) {
        Phone_No = phone_No;
    }

    public LocalDate getDate_Of_Birth() {
        return Date_Of_Birth;
    }

    public void setDate_Of_Birth(LocalDate date_Of_Birth) {
        Date_Of_Birth = date_Of_Birth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getPassword() {
        return Password;
    }

    public void setPassword(int password) {
        Password = password;
    }
}
