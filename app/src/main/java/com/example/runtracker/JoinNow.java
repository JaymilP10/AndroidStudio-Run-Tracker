package com.example.runtracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class JoinNow extends AppCompatActivity {

    // initialize variables

    Button join;
    TextView textView, dropdown_menu;
    boolean[] selectedWorkout;

    boolean[] selectedRace;
    ArrayList<Integer> langList = new ArrayList<>();

    ArrayList<Integer> raceList = new ArrayList<>();
    String[] langArray = {"Long Run (7 miles)", "Easy Run (3 miles)", "Intervals (12x400m)", "Tempo Run (4x1mile)", "Race (5k)"};

    String[] raceArray = {"1 mile", "5K", "10K", "Half Marathon", "Marathon"};

    EditText editUserID;
    EditText editUserName;
    EditText editUserPassword;

    Button btnInsert, btnFetch, btnUpdate, btnDelete;

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinnow);

        // assign variable
        textView = findViewById(R.id.selectWorkouts);
        dropdown_menu = findViewById(R.id.dropdown_menu);

        // initialize selected language array
        selectedWorkout = new boolean[langArray.length];

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinNow.this);

                // set title
                builder.setTitle("Select Workouts");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(langArray, selectedWorkout, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            // concat array value
                            stringBuilder.append(langArray[langList.get(j)]);
                            // check condition
                            if (j != langList.size() - 1) {
                                // When j value not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedWorkout.length; j++) {
                            // remove all selection
                            selectedWorkout[j] = false;
                            // clear language list
                            langList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

        dropdown_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinNow.this);

                // set title
                builder.setTitle("Select Race");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(raceArray, selectedRace, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position in lang list
                            raceList.add(i);
                            // Sort array list
                            Collections.sort(raceList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            raceList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < raceList.size(); j++) {
                            // concat array value
                            stringBuilder.append(raceArray[raceList.get(j)]);
                            // check condition
                            if (j != raceList.size() - 1) {
                                // When j value not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        dropdown_menu.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedRace.length; j++) {
                            // remove all selection
                            selectedRace[j] = false;
                            // clear language list
                            raceList.clear();
                            // clear text view value
                            dropdown_menu.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });

//        editUserID = (EditText) findViewById(R.id.editTextID);
        editUserName = (EditText) findViewById(R.id.editTextUsername);
        editUserPassword = (EditText) findViewById(R.id.editTextPassword);

        btnInsert = (Button) findViewById(R.id.buttonInsert);
//        btnFetch = (Button) findViewById(R.id.buttonFetch);
//        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
//        btnDelete = (Button) findViewById(R.id.buttonDelete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinNow.this, StartRun.class);
                startActivity(intent);
                btnInsertPressed(view);
            }
        });

//        btnFetch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btnFetchPressed(view);
//            }
//        });
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btnUpdatePressed(view);
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btnDeletePressed(view);
//            }
//        });

        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnInsertPressed(View v){
        dbManager.insert(editUserName.getText().toString(), editUserPassword.getText().toString());
    }

    public void btnFetchPressed(View v){
        Cursor cursor = dbManager.fetch();

        if (cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_ID));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD));
                Log.i("DATABASE_TAG", "I have read ID : " + ID + " Username : " + username + " password : " + password);

            } while (cursor.moveToNext());
        }
    }

    public void btnUpdatePressed(View v) {
        dbManager.update(Long.parseLong(editUserID.getText().toString()), editUserName.getText().toString(), editUserPassword.getText().toString());
    }

    public void btnDeletePressed(View v){
        dbManager.delete(Long.parseLong(editUserID.getText().toString()));
    }
}
