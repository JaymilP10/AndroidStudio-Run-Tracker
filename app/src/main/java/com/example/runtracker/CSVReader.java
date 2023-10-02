package com.example.runtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVReader {
    public static void loadCSVFile(@NotNull Context context, @NotNull String fileName, @NotNull SQLiteDatabase database) {
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

//                int id = Integer.parseInt(values[0]);
//                double distance = Double.parseDouble(values[0]);
//                int duration = Integer.parseInt(values[1]);
//                String date = values[2];
//                String location = values[3];
//                String weather = values[4];
//                String type = values[5];
//                int effort = Integer.parseInt(values[6]);

                int id = Integer.parseInt(values[0]);
                int distance = Integer.parseInt(values[1]);
                int duration = Integer.parseInt(values[2]);
                String date = values[3];
                String location = values[4];
                String weather = values[5];
                String type = values[6];
                int effort = Integer.parseInt(values[7]);

                ContentValues contentValues = new ContentValues();
                contentValues.put(SQLiteManager.COLUMN_ID, id);
                contentValues.put(SQLiteManager.COLUMN_DISTANCE, distance);
                contentValues.put(SQLiteManager.COLUMN_DURATION, duration);
                contentValues.put(SQLiteManager.COLUMN_DATE, date);
                contentValues.put(SQLiteManager.COLUMN_LOCATION, location);
                contentValues.put(SQLiteManager.COLUMN_WEATHER, weather);
                contentValues.put(SQLiteManager.COLUMN_TYPE, type);
                contentValues.put(SQLiteManager.COLUMN_EFFORT, effort);

                database.insert(SQLiteManager.TABLE_NAME, null, contentValues); // insert the values into the database
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
