package com.example.runtracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DatabaseHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "MY_COMPANY.DB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "USERS";
    static final String USER_ID = "_ID";
    static final String USER_NAME = "user_name";
    static final String USER_PASSWORD = "password";

    private static final String CREATE_DR_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT NOT NULL, " + USER_PASSWORD + " TEXT NOT NULL);" ;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DR_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }
}

//public class DatabaseHelper extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "MyDatabase";
//    private static final int DATABASE_VERSION = 1;
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Create your database tables here
//        String createTableQuery = "CREATE TABLE IF NOT EXISTS your_table_name ("
//                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + "column_name TEXT)";
//        db.execSQL(createTableQuery);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Handle database upgrades (e.g., alter table structure)
//        db.execSQL("DROP TABLE IF EXISTS your_table_name");
//        onCreate(db);
//    }
//
//    DatabaseHelper dbHelper = new DatabaseHelper(context);
//    SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//    ContentValues values = new ContentValues();
//    values.put("column_name", "some_value");
//    long newRowId = db.insert("your_table_name", null, values);
//
//    String[] projection = {"_id", "column_name"};
//    Cursor cursor = db.query("your_table_name", projection, null, null, null, null, null);
//
//    while (cursor.moveToNext()) {
//        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
//        String value = cursor.getString(cursor.getColumnIndexOrThrow("column_name"));
//        // Process data
//    }
//    cursor.close();
//
//    ContentValues values = new ContentValues();
//    values.put("column_name", "new_value");
//    String selection = "_id=?";
//    String[] selectionArgs = {String.valueOf(id)};
//    int count = db.update("your_table_name", values, selection, selectionArgs);
//
//    String selection = "_id=?";
//    String[] selectionArgs = {String.valueOf(id)};
//    int deletedRows = db.delete("your_table_name", selection, selectionArgs);
//
//
//
//}
