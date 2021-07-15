package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class studentCoursePage extends AppCompatActivity {


    Button btnEnrol, btnUnEnrol;

    TextView txtCourseName, txtCourseID, txtCapacity, txtTeacher, txtDescription, txtDay1,txtDay2;
    int capacity;
    String courseName, studentName;
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

        txtTeacher = findViewById(R.id.txtTeacher);
        txtDescription= findViewById(R.id.txtDescription);
        txtDay1 = findViewById(R.id.txtDay1);
        txtDay2 = findViewById(R.id.txtDay2);
        db = new NewDBHandler(this);



        courseName = getIntent().getStringExtra("courseName");
        studentName = getIntent().getStringExtra("instructorName");
        currentCourse = db.findCourse(courseName);
        capacity = currentCourse.getStudent_capacity();



        btnUnEnrol.setVisibility(View.GONE);
        btnUnEnrol.setEnabled(false);




        String day1, day2;
        String temp = currentCourse.getDays();
        if (temp.contains("&")){
            String[] parts = temp.split("&");
            day1 = parts[0];
            day2 = parts[1];
        }else{
            day1 = "NA";
            day2 = "NA";
        }

        txtCourseName.setText(courseName);
        txtCapacity.setText("Current course capacity: " + String.valueOf(currentCourse.getStudent_capacity()-currentCourse.getStudentsEnrolled()));
        txtCourseID.setText(currentCourse.getCode());
        txtDay1.setText(day1);
        txtDay2.setText(day2);
        txtDescription.setText("Course Description: " +currentCourse.getDescription());
        txtTeacher.setText(currentCourse.getInstructor());


        if(db.isStudentInCourse(studentName, courseName)){
            btnEnrol.setVisibility(View.GONE);
            btnUnEnrol.setVisibility(View.VISIBLE);
            btnUnEnrol.setEnabled(true);
        }
        else if(db.isConflict(currentCourse, studentName)){
            Toast.makeText(getApplicationContext(),"There is a time conflict in your schedule.", Toast.LENGTH_SHORT).show();
            btnEnrol.setVisibility(View.GONE);
        }


        btnEnrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capacity > 0){



                    db.enroll(studentName, courseName);
                    Course temp = db.findCourse(courseName);
                    txtCapacity.setText("Current course capacity: " + String.valueOf(temp.getStudent_capacity()-temp.getStudentsEnrolled()));


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


                db.unenroll(studentName, courseName);


                Course temp = db.findCourse(courseName);
                txtCapacity.setText("Current course capacity: " + String.valueOf(temp.getStudent_capacity()-temp.getStudentsEnrolled()));


                btnUnEnrol.setEnabled(false);
                btnUnEnrol.setVisibility(View.GONE);

                btnEnrol.setEnabled(true);
                btnEnrol.setVisibility(View.VISIBLE);
            }
        });




    }
}