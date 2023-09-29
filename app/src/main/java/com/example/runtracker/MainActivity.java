package com.example.runtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button joinNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinNow = (Button) findViewById(R.id.joinnow);
        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JoinNow.class);
                startActivity(intent);
            }
        });

        DBHandler.instance = new DBHandler(this);

        Button button = findViewById(R.id.login);
        button.setOnClickListener(
                (view) -> { // this is a lambda expression, just easier instead of the whole @Override nonsense
                    CSVReader.loadCSVFile(MainActivity.this, "sample.csv", DBHandler.instance.getWritableDatabase());
                    Toast.makeText(this, "Loaded CSV file into Database", Toast.LENGTH_SHORT).show();
                });


//        Button load = findViewById(R.id.show_entries);
//        load.setOnClickListener(
//                (view) -> {
//                    String allEntries =
//                            DBHandler.instance.getAll().stream().reduce("", (a, b) -> a + "\n" + b);
//
//                    ListView listView = findViewById(R.id.listView);
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, DBHandler.instance.getAll());
//                    listView.setAdapter(adapter);
//
//                    Log.d("CSV File Content", allEntries);
//                });
//
//        findViewById(R.id.delete_all).setOnClickListener(view -> {
//            DBHandler.instance.deleteAll();
//            Toast.makeText(this, "Deleted all entries", Toast.LENGTH_SHORT).show();
//        });

        /*
         * To get the CSV file to load, you need to put it in the assets folder. `src/main/assets/file.csv`
         * You can look at the method call of CSVReader#loadCSVFile to see how it works (hint, it's just BufferedReader from CSA)
         *
         *
         * */
    }
}