package com.example.runtracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydb";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "sample_table";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_WEATHER = "weather";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_EFFORT = "effort";

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy:HH:mm:ss");

    public static DBHandler instance;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DBHandler(context);
        }
        Log.d("DBHandler", "getInstance: " + instance);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DISTANCE + " INTEGER, " + COLUMN_DURATION + " INTEGER, " + COLUMN_DATE + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " + COLUMN_WEATHER + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_EFFORT + " INTEGER);";

        db.execSQL(query);
//        String createTableQuery = String.format(
//                "CREATE TABLE %s (%s TEXT, %s INTEGER, %s TEXT)",
//                TABLE_NAME, COLUMN_NAME, COLUMN_AGE, COLUMN_CONTENT);
//        Log.d("ExecSQL", createTableQuery);
//        db.execSQL(createTableQuery);
//        Log.d("DBHandler", "onCreate: " + createTableQuery);
    }

    public void addNoteToDatabase(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, note.getId());
        contentValues.put(COLUMN_DISTANCE, note.getDistance());
        contentValues.put(COLUMN_DURATION, note.getDuration());
        contentValues.put(COLUMN_DATE, getStringFromDate(note.getDate()));
        contentValues.put(COLUMN_LOCATION, note.getLocation());
        contentValues.put(COLUMN_WEATHER, note.getWeather());
        contentValues.put(COLUMN_TYPE, note.getType());
        contentValues.put(COLUMN_EFFORT, note.getEffort());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateNoteListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        int i = 0;

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null))
        {
            if(result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt(1);
                    int distance = result.getInt(2);
                    int duration = result.getInt(3);
                    Date date = getDateFromString(result.getString(4));
                    String location = result.getString(5);
                    String weather = result.getString(6);
                    String type = result.getString(7);
                    int effort = result.getInt(8);
                    Note note = new Note(id, distance, duration, date, location, weather, type, effort);
                    Note.noteArrayList.add(note);
                    i++;
                }
            }
        }
    }

    private String getStringFromDate(Date date)
    {
        if(date == null)
            return null;
        return dateFormat.format(date);
    }

    private Date getDateFromString(String string)
    {
        try
        {
            return dateFormat.parse(string);
        }
        catch (ParseException | NullPointerException e)
        {
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public ArrayList<String> getAll() {
        String select = String.format("SELECT * FROM %s", TABLE_NAME);
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(select, null);
        ArrayList<String> returnThingy = new ArrayList<>();
        while (cursor.moveToNext()) {
            String firstName = cursor.getString(0); // same indices as the csv / create table query
            int age = cursor.getInt(1);
            String content = cursor.getString(2);
            returnThingy.add("name: " + firstName + " age: " + age + " content: " + content);
        }
        cursor.close();
        database.close();
        return returnThingy;
    }

    public void deleteAll() {
        String delete = String.format("DELETE FROM %s", TABLE_NAME);
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(delete);
        database.close();
    }
}
