package com.example.runtracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Random;

public class ProgressScreen extends AppCompatActivity {

    BarChart barChart;

    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressscreen);

        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("January");
        theDates.add("February");
        theDates.add("March");
        theDates.add("April");
        theDates.add("May");
        theDates.add("June");
        theDates.add("July");
        theDates.add("August");
        theDates.add("September");
        theDates.add("October");
        theDates.add("November");
        theDates.add("December");
    }
}
