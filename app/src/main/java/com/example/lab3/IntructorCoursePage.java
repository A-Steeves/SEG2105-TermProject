package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class IntructorCoursePage extends AppCompatActivity {

    Button btnAssign, btnEdit, btnUnassign;
    EditText text1, text3, courseTime, text4;

    NewDBHandler db;
    Course currentCourse;

    private TextView cName, cID, cInstructor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intructor_course_page);

        db = new NewDBHandler(this);
        //currentCourse = db.findCourse(courseName);




        /*
        For Ajay

        intent.putExtra("courseName", courseName);

        intent.putExtra("courseID", courseID);

        intent.putExtra("instructorName", instructorName);



        //Not this part
        String courseName = getIntent().getStringExtra("courseName");

        String courseID = getIntent().getStringExtra("courseID");

        String instructorName = getIntent().getStringExtra("instructorName");
        */


        String courseName = "Software Engineering";

        String courseID = "SEG2136";

        String instructorName = "Samantha Sam";



        cName = (TextView)findViewById(R.id.courseName);
        cID = (TextView)findViewById(R.id.courseID);
        cInstructor = (TextView)findViewById(R.id.courseInstructor);

        cName.setText(courseName);
        cID.setText(courseID);


        btnAssign = (Button)findViewById(R.id.btnAssign);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnUnassign = (Button)findViewById(R.id.btnUnassign);





        text1 = (EditText)findViewById(R.id.textName);
        courseTime = (EditText)findViewById(R.id.courseTime);
        text3 = (EditText)findViewById(R.id.text3);
        text4 = (EditText)findViewById(R.id.text4);

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

        /*
        if(currentCourse.getInstructor() == instructorName) {
            btnAssign.setEnabled(False);
            cInstructor.setText(instructorName);

            text1.setText(currentCourse.getDays());
            courseTime.setText(currentCourse.getHours());
            text3.setText(currentCourse.getDescription());
            text4.setText(currentCourse.getStudent_Capacity());


            btnEdit.setVisibility(View.VISIBLE);
            btnUnassign.setVisibility(View.VISIBLE);


        }
        else{
            if(courseExists && courseInstructor != db.course.instructor)
                btnAssign.setEnabled(False)
                btnEdit.setEnabled(False)
                btnUnassign.setEnabled(False)

                cInstructor.setText(currentCourse.getInstructor());

                text1.setText(currentCourse.getDays());
                courseTime.setText(currentCourse.getHours());
                text3.setText(currentCourse.getDescription());
                text4.setText(currentCourse.getStudent_Capacity());


         */

        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.VISIBLE);
                btnUnassign.setVisibility(View.VISIBLE);

                //cInstructor.setText(instructorName);
                //currentCourse.setInstructor(instructorName)
                //btnAssign.setEnabled(False)

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPage();





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


                text1.setVisibility(View.GONE);
                courseTime.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.GONE);


                //db.removeCourseInfo(courseName);
                //btnAssign.setEnabled(False)
            }
        });



    }
    private void testPage(){
        Intent intent = new Intent (this, courseInfo.class);
        startActivity(intent);
    }


}