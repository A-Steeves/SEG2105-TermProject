package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class IntructorCoursePage extends AppCompatActivity {

    Button btnAssign, btnEdit, btnUnassign, btnSave;
    EditText text1, text3, text4;

    NewDBHandler db;
    Course currentCourse;

    private TextView cName, cID, cInstructor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intructor_course_page);

        db = new NewDBHandler(this);
        //currentCourse = db.findCourse(courseName);


        //CourseTIME
        Spinner courseTime = (Spinner)findViewById(R.id.courseTime);
        String[] types = new String[]{"8:30 AM", "10:00 AM", "11:30 AM", "1:00 PM", "2:30 PM", "4:00 PM", "5:30 PM", "7:00 PM", "8:30 PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        courseTime.setAdapter(adapter);

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
        btnSave = (Button)findViewById(R.id.btnSave);




        text1 = (EditText)findViewById(R.id.textName);
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


        btnSave.setVisibility(View.GONE);
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

            btnSave.setVisibility(View.VISIBLE);
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
                btnEdit.setVisibility(View.GONE);
                btnUnassign.setVisibility(View.GONE);
                btnSave.setVisibility(View.VISIBLE);


                text1.setEnabled(true);
                courseTime.setEnabled(true);
                text3.setEnabled(true);
                text4.setEnabled(true);

                text1.setVisibility(View.VISIBLE);
                courseTime.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.VISIBLE);



            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.VISIBLE);
                btnUnassign.setVisibility(View.VISIBLE);

                btnSave.setVisibility(View.GONE);

                text1.setEnabled(false);
                courseTime.setEnabled(false);
                text3.setEnabled(false);
                text4.setEnabled(false);



                //currentCourse.setDays(findViewById(R.id.textName));
                //currentCourse.setHours((EditText)findViewById(R.id.courseTime));
                //currentCourse.setDescription(findViewById(R.id.text3));
                //currentCourse.setStudentCapacity(findViewById(R.id.text4));

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


}