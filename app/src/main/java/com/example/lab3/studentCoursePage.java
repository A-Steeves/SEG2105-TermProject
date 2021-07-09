package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class studentCoursePage extends AppCompatActivity {


    Button btnEnrol, btnUnEnrol;

    TextView txtCourseName, txtCourseID, txtCapacity;
    int capacity;
    String courseName;
    Course currentCourse;
    NewDBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_page);


        btnEnrol = (Button)findViewById(R.id.btnEnrol);
        btnUnEnrol = (Button)findViewById(R.id.btnUnEnrol);

        txtCourseName = findViewById(R.id.txtCourseName);
        txtCourseID = findViewById(R.id.txtCourseId);
        txtCapacity = findViewById(R.id.txtCapacity);
        db = new NewDBHandler(this);

        /*

        courseName = getIntent().getStringExtra("courseName");
        currentCourse = db.findCourse(courseName);
        capacity = currentCourse.getCapacity();

         */

        btnUnEnrol.setVisibility(View.GONE);
        btnUnEnrol.setEnabled(false);


        btnEnrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capacity > 0){
                    /*
                    decrementCourseCapacity();   //We might need to make a method in database to do this.

                    AddStudent to course List.
                    Add course to student list.



                     */
                    btnUnEnrol.setEnabled(true);
                    btnUnEnrol.setVisibility(View.VISIBLE);
                    btnEnrol.setEnabled(false);
                } else{
                    Toast.makeText(getApplicationContext(),"Course is full.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnUnEnrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                incrementCourseCapacity();
                remove student from course list.
                remove course from student list.


                 */
                btnUnEnrol.setEnabled(false);
                btnUnEnrol.setVisibility(View.GONE);
            }
        });




    }
}