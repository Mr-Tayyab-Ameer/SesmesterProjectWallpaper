package com.example.appwallpaper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.db";
    private static final int DATABASE_VERSION = 1;
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_PASSWORD="password";
    private static final String TABLE_USERS="users";
    boolean f=false;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE users ("+
                    "username TEXT," +
                    "password TEXT PRIMARY KEY)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS users";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public boolean getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        try {
            String[] projection = {
                    COLUMN_USERNAME,
                    COLUMN_PASSWORD
            };
            String selection = "SELECT * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};

            cursor = db.rawQuery(selection, selectionArgs);

            if (cursor != null && cursor.moveToFirst()) {
               f=true;
            }
        } catch (Exception e) {
            Log.e("DbHelper", "Login Failed " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return f;
    }
    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long newRowId = db.insert(TABLE_USERS, null, values);
        db.close();
        return newRowId; // Returns the primary key value of the new row
    }

}
