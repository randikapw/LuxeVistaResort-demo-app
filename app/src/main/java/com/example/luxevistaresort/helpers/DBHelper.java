package com.example.luxevistaresort.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.luxevistaresort.models.User;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ExampleApp.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PROFILE_IMAGE = "profile_image";
    public static final String COLUMN_NIC = "nic";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_CONTACT + " TEXT, " +
                COLUMN_NIC + " TEXT, " +
                COLUMN_DOB + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_PROFILE_IMAGE + " BLOB)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean insertUser(String name, String email, String contact, String nic, String dob, String gender, String role, String password, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_NIC, nic);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_ROLE, role);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PROFILE_IMAGE, image);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE email = ? AND password = ?",
                new String[]{email, password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String contact = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT));
            String nic = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIC));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOB));
            byte[] profileImage = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_PROFILE_IMAGE));

            cursor.close();
            return new User(name, email, contact, nic, gender, dob, profileImage);
        }

        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

}

