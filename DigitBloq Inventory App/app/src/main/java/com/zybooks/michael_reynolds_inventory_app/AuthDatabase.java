package com.zybooks.michael_reynolds_inventory_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AuthDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "auth.db";
    private static final int VERSION = 1;

    public AuthDatabase(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class UserTable {
        public static final String TABLE = "auth";
        public static final String COL_ID = "_id";
        public static final String COL_USERNAME = "username";
        public static final String COL_PASSWORD = "password";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " +
                UserTable.COL_USERNAME + " text, " +
                UserTable.COL_PASSWORD + " text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserTable.TABLE);
        onCreate(db);
    }

    public void addUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UserTable.COL_USERNAME, userModel.getUsername());
        cv.put(UserTable.COL_PASSWORD, userModel.getPassword());

        db.insert(UserTable.TABLE, null, cv);
        db.close();
    };

    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                UserTable.COL_USERNAME,
                UserTable.COL_PASSWORD
        };

        String selection = UserTable.COL_USERNAME + " = ? AND " +
                UserTable.COL_PASSWORD + " = ?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.query(
                UserTable.TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() > 0) {
            // User is valid, do something here
            cursor.close();

            db.close();

            return true;
        } else {
            // User is not valid, show an error message
            cursor.close();

            db.close();

            return false;
        }
    }
}
