package com.example.luxevistaresort.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.example.luxevistaresort.models.User;

public class UserManager {
    private static final String PREF_NAME = "UserPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_NIC = "nic";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_PROFILE_IMAGE = "profile_image";

    private SharedPreferences preferences;
    private User currentUser;

    public UserManager(Context context) {
        preferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        loadUser();
    }

    public void saveUser(User user) {
        currentUser = user;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_CONTACT, user.getContact());
        editor.putString(KEY_NIC, user.getNic());
        editor.putString(KEY_GENDER, user.getGender());
        editor.putString(KEY_DOB, user.getDob());
        
        if (user.getProfileImage() != null) {
            String imageStr = Base64.encodeToString(user.getProfileImage(), Base64.DEFAULT);
            editor.putString(KEY_PROFILE_IMAGE, imageStr);
        }
        
        editor.apply();
    }

    private void loadUser() {
        String name = preferences.getString(KEY_NAME, null);
        if (name != null) {
            String email = preferences.getString(KEY_EMAIL, "");
            String contact = preferences.getString(KEY_CONTACT, "");
            String nic = preferences.getString(KEY_NIC, "");
            String gender = preferences.getString(KEY_GENDER, "");
            String dob = preferences.getString(KEY_DOB, "");
            
            byte[] profileImage = null;
            String imageStr = preferences.getString(KEY_PROFILE_IMAGE, null);
            if (imageStr != null) {
                profileImage = Base64.decode(imageStr, Base64.DEFAULT);
            }

            currentUser = new User(name, email, contact, nic, gender, dob, profileImage);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void clearUser() {
        currentUser = null;
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }
} 