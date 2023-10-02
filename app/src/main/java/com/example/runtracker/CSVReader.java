package com.example.runtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVReader {
    public static void loadCSVFile(Context context, String fileName, SQLiteDatabase database) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        try {
            inputStream = context.getAssets().open(fileName);
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            reader.readLine(); // ignore the header (first) line

            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // read the comma-separated line

                String id = values[0];
                int distance = Integer.parseInt(values[1]);
                String duration = values[2];
                String date = values[3];
                String location = values[4];
                String weather = values[5];
                String type = values[6];
                String effort = values[7];

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHandler.COLUMN_ID, id);
                contentValues.put(DBHandler.COLUMN_DISTANCE, distance);
                contentValues.put(DBHandler.COLUMN_DURATION, duration);
                contentValues.put(DBHandler.COLUMN_DATE, date);
                contentValues.put(DBHandler.COLUMN_LOCATION, location);
                contentValues.put(DBHandler.COLUMN_WEATHER, weather);
                contentValues.put(DBHandler.COLUMN_TYPE, type);
                contentValues.put(DBHandler.COLUMN_EFFORT, effort);

                database.insert(DBHandler.TABLE_NAME, null, contentValues); // insert the values into the database
                Log.d("CSV Insert", "Inserting: " + contentValues);
                // content values are a map, but they make it easier opposed to doing it manually like INSERT INTO ...
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // free up resources by closing them
            // just good practice
            try {
                if (reader != null) reader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
                if (database != null) database.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
