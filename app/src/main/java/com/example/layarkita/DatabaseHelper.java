package com.example.layarkita;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "layarKita.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Buat tabel users
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_EMAIL + " TEXT PRIMARY KEY," +
                COLUMN_PASSWORD + " TEXT" +
                ")";
        db.execSQL(CREATE_USERS_TABLE);

        // Tambah user dummy
        String INSERT_DUMMY_USER = "INSERT INTO " + TABLE_USERS + " VALUES('admin@example.com', '123456')";
        db.execSQL(INSERT_DUMMY_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Cek login
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }
}