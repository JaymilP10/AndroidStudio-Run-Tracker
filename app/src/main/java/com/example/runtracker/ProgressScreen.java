package com.example.runtracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Random;

public class ProgressScreen extends AppCompatActivity {

    private SQLiteManager sqLiteManager;

    SQLiteDatabase sqLiteDatabase;

    BarChart barChart;
    TabLayout tabLayout;
    TabLayout.Tab tab;
    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressscreen);

        barChart = (BarChart) findViewById(R.id.bargraph);

        sqLiteManager = new SQLiteManager(this);
        sqLiteDatabase = sqLiteManager.getWritableDatabase();

        barEntries = sqLiteManager.getBarEntries();

        BarDataSet barDataSet = new BarDataSet(barEntries, "Distance by Day");

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);

        barChart.setData(barData);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Intent intent = new Intent(ProgressScreen.this, ProgressScreen.class);
                startActivity(intent);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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

    public void mPlotWeeklyBarGraph (ArrayList yValBarOne) {

        BarDataSet barDataSet1 = new BarDataSet(yValBarOne, "Week One");
        barDataSet1.setColor(Color.RED);
        float gs = 0.1f;
        float bs = 0.02f;
        float bw = 0.43f;
        BarData barData = new BarData(barDataSet1);
        barData.setDrawValues(false);
        barChart.setData(barData);
        barData.setBarWidth(bw);
        barChart.groupBars(0, gs, bs);
        barData.setValueFormatter(new LargeValueFormatter());
        XAxis xAxis = barChart.getXAxis();
        ArrayList<String> theDays = new ArrayList<>();
        theDays.add("M");
        theDays.add("T");
        theDays.add("W");
        theDays.add("T");
        theDays.add("F");
        theDays.add("S");
        theDays.add("S");
        xAxis.setValueFormatter(new IndexAxisValueFormatter(theDays));
        barChart.getDescription().setEnabled(false);
        barChart.invalidate();
    }
}
