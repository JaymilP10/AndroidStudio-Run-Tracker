package com.example.runtracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ScrollCaptureTarget;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarScreen extends AppCompatActivity {
    CalendarView calendar;
    TextView date, distance, time, whereiran, type, selectedDateText;
    String selectedDate, bedtime, waketime, hourNum;

    Button Run;

    boolean dateCheck = false;

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarscreen);

        Run = (Button) findViewById(R.id.Run);
        Run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarScreen.this, StartRun.class);
                startActivity(intent);
            }
        });

        calendar = (CalendarView) findViewById(R.id.calendar);
        distance = (TextView) findViewById(R.id.distance);
        time = (TextView) findViewById(R.id.time);
        whereiran = (TextView) findViewById(R.id.whereiran);
        type = (TextView) findViewById(R.id.type);
        // Add Listener in calendar
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;

                System.out.println(Date);

                Calendar c1 = Calendar.getInstance();

                // set Month
                // MONTH starts with 0 i.e. ( 0 - Jan)
                c1.set(Calendar.MONTH, month);

                // set Date
                c1.set(Calendar.DATE, dayOfMonth);

                // set Year
                c1.set(Calendar.YEAR, year);

                System.out.println(c1.get(Calendar.MONTH));
                System.out.println(c1.get(Calendar.DATE));
                System.out.println(c1.get(Calendar.YEAR));
                System.out.println(Note.noteArrayList.get(0).getDate());
                for(int i = 0; i < Note.noteArrayList.size(); i++) {
//                    System.out.println(dateFormat.format(Note.noteArrayList.get(i).getStartTime()));
//                    System.out.println(dateFormat.format(Note.noteArrayList.get(i).getEndTime()));
//                    System.out.println(dateFormat.format(Note.noteArrayList.get(i).getStartTime()).substring(0, 2));
                    //month
//                    System.out.println(dateFormat.format(Note.noteArrayList.get(i).getStartTime()).substring(3, 5));
                    //day
//                    System.out.println(dateFormat.format(Note.noteArrayList.get(i).getStartTime()).substring(6, 10));
                    //year

                    if(c1.get(Calendar.MONTH) + 1 == Integer.parseInt(dateFormat.format(Note.noteArrayList.get(i).getDate()).substring(0, 2)) &&
                            c1.get(Calendar.DATE) == Integer.parseInt(dateFormat.format(Note.noteArrayList.get(i).getDate()).substring(3, 5)) &&
                            c1.get(Calendar.YEAR) == Integer.parseInt(dateFormat.format(Note.noteArrayList.get(i).getDate()).substring(6, 10))){
                        System.out.println("HELLO");
                        dateCheck = true;
                        System.out.println(" " + dateFormat.format(Note.noteArrayList.get(i).getDate()));


                        selectedDate = getMonthText(Integer.parseInt(dateFormat.format(Note.noteArrayList.get(i).getDate()).substring(0, 2))) + " " + dateFormat.format(Note.noteArrayList.get(i).getDate()).substring(3, 5) + ", " + dateFormat.format(Note.noteArrayList.get(i).getDate()).substring(6, 10);
                        System.out.println(selectedDate);
                        selectedDateText.setText(selectedDate);

                    }
                }
                if(!dateCheck){
                    System.out.println("WORKSSS");
                    distance.setText(" ");
                    type.setText(" ");
                    whereiran.setText(" ");
                    time.setText(" ");

                    String displayDate = getMonthText(Integer.parseInt(String.valueOf(c1.get(Calendar.MONTH))) + 1) + " " + c1.get(Calendar.DATE) + ", " + c1.get(Calendar.YEAR);
                    selectedDateText.setText(displayDate);
                }
                dateCheck = false;
            }
        });
    }

    public String getMonthText(int m){
        if(m == 1){
            return "January";
        }
        if(m == 2){
            return "February";
        }
        if(m == 3){
            return "March";
        }
        if(m == 4){
            return "April";
        }
        if(m == 5){
            return "May";
        }
        if(m == 6){
            return "June";
        }
        if(m == 7){
            return "July";
        }
        if(m == 8){
            return "August";
        }
        if(m == 9){
            return "September";
        }
        if(m == 10){
            return "October";
        }
        if(m == 11){
            return "November";
        }
        return "December";
    }
}
