package com.example.runtracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
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

        ArrayList<String> theMonths = new ArrayList<>();
        theMonths.add("January");
        theMonths.add("February");
        theMonths.add("March");
        theMonths.add("April");
        theMonths.add("May");
        theMonths.add("June");
        theMonths.add("July");
        theMonths.add("August");
        theMonths.add("September");
        theMonths.add("October");
        theMonths.add("November");
        theMonths.add("December");

        ArrayList<String> theDays = new ArrayList<>();
        theDays.add("M");
        theDays.add("T");
        theDays.add("W");
        theDays.add("T");
        theDays.add("F");
        theDays.add("S");
        theDays.add("S");
    }
}
