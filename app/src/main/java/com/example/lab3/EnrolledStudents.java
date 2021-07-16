package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EnrolledStudents extends AppCompatActivity {
    ListView list;
    ArrayList<String> students;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_students);


        list = (ListView) findViewById(R.id.SHOWLIST);

        NewDBHandler dbHandler = new NewDBHandler(this);

        students = dbHandler.studentsInCourse(this.getIntent().getStringExtra("courseN"));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);

        list.setAdapter(adapter);

    }
}