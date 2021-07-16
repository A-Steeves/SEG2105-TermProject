package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class EnrolledStudents extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_students);
        NewDBHandler db = new NewDBHandler(this);

        String courseName = getIntent().toString();
        list = findViewById(R.id.SHOW);


    }
}