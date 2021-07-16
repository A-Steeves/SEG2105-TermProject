package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class IntructorCoursePage extends AppCompatActivity  {

    Button btnAssign, btnEdit, btnUnassign, btnStudent;
    EditText text1, text3, courseTime, text4;
    Course currentCourse;
    NewDBHandler db;

    String courseDays, courseDescription;
    int courseCapacity;

    String courseName, courseID, instructorName;

    private TextView cName, cID, cInstructor;
    @Override
    @SuppressWarnings("serial")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intructor_course_page);


        //Not this part
        courseName = getIntent().getStringExtra("courseName");
        courseID = getIntent().getStringExtra("courseID");
        instructorName = getIntent().getStringExtra("instructorName");




        db = new NewDBHandler(this);

        currentCourse = db.findCourse(courseName);
        cName = (TextView)findViewById(R.id.courseName);
        cID = (TextView)findViewById(R.id.courseID);
        cInstructor = (TextView)findViewById(R.id.courseInstructor);

        cName.setText(courseName);
        cID.setText(courseID);


        btnAssign = (Button)findViewById(R.id.btnAssign);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnUnassign = (Button)findViewById(R.id.btnUnassign);

        btnStudent = (Button)findViewById(R.id.btnStudents);




        text1 = (EditText)findViewById(R.id.textName);
        text3 = (EditText)findViewById(R.id.text3);
        text4 = (EditText)findViewById(R.id.text4);
        courseTime = (EditText)findViewById(R.id.courseTime);

        //Course INFO UNABLE TO SEE OR INTERACT
        text1.setEnabled(false);
        courseTime.setEnabled(false);
        text3.setEnabled(false);
        text4.setEnabled(false);


        text1.setVisibility(View.GONE);
        courseTime.setVisibility(View.GONE);
        text3.setVisibility(View.GONE);
        text4.setVisibility(View.GONE);

        //Done



        btnEdit.setVisibility(View.GONE);
        btnUnassign.setVisibility(View.GONE);

        btnAssign.setEnabled(true);

        btnStudent.setVisibility(View.GONE);

        if(currentCourse.getInstructor().equals(instructorName)) {
            btnAssign.setEnabled(false);
            btnEdit.setVisibility(View.VISIBLE);
            btnUnassign.setVisibility(View.VISIBLE);

            //Toast.makeText(getApplicationContext(),currentCourse.getDays(), Toast.LENGTH_SHORT).show();

            btnStudent.setVisibility(View.VISIBLE);

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
            cInstructor.setText(instructorName);
            text1.setText(day1);
            courseTime.setText(day2);
            text3.setText(currentCourse.getDescription());
            String beef = String.valueOf(currentCourse.getStudent_capacity());
            text4.setText(beef+" Students");

            text1.setVisibility(View.VISIBLE);
            courseTime.setVisibility(View.VISIBLE);
            text3.setVisibility(View.VISIBLE);
            text4.setVisibility(View.VISIBLE);


        }

        if (!currentCourse.getInstructor().equals(instructorName)  && !currentCourse.getInstructor().equals("NA")) {
            btnAssign.setEnabled(false);
            btnEdit.setEnabled(false);
            btnUnassign.setEnabled(false);

            //Toast.makeText(getApplicationContext(),"Here2", Toast.LENGTH_SHORT).show();

            cInstructor.setText(currentCourse.getInstructor());

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

            text1.setText(day1);
            courseTime.setText(day2);
            text3.setText(currentCourse.getDescription());
            String beef = String.valueOf(currentCourse.getStudent_capacity());
            text4.setText(beef+" Students");

            text1.setVisibility(View.VISIBLE);
            courseTime.setVisibility(View.VISIBLE);
            text3.setVisibility(View.VISIBLE);
            text4.setVisibility(View.VISIBLE);

        }



        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.VISIBLE);
                btnUnassign.setVisibility(View.VISIBLE);

                cInstructor.setText(instructorName);

                db.assignInstructor(courseName, instructorName);

                btnAssign.setEnabled(false);
                btnStudent.setVisibility(View.VISIBLE);

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPage(instructorName, courseName);


                text1.setVisibility(View.VISIBLE);
                courseTime.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.VISIBLE);



            }
        });



        btnUnassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.GONE);
                btnUnassign.setVisibility(View.GONE);
                cInstructor.setText("");
                btnAssign.setEnabled(true);


                text1.setVisibility(View.GONE);
                courseTime.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.GONE);
                btnStudent.setVisibility(View.GONE);


                text1.setText("");
                courseTime.setText("");
                text3.setText("");
                text4.setText("");
                db.unassignInstructor(courseName);

            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentEnrolled(courseName);
            }
        });





    }


    public void testPage(String name, String courseName){

        Intent intent = new Intent(this, courseInfo.class);
        intent.putExtra("TeachName", name);
        intent.putExtra("courseN", courseName);
        startActivity(intent);

    }

    public void studentEnrolled(String courseName){
        Intent inte= new Intent(this, courseInfo.class);

        inte.putExtra("courseN", courseName);
        startActivity(inte);
    }


}