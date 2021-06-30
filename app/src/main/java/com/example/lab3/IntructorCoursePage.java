package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class IntructorCoursePage extends AppCompatActivity {

    Button btnAssign, btnEdit, btnUnassign, btnSave;
    EditText text1, text2, text3, text4;

    private TextView cName, cID, cInstructor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intructor_course_page);

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
        text2 = (EditText)findViewById(R.id.text2);
        text3 = (EditText)findViewById(R.id.text3);
        text4 = (EditText)findViewById(R.id.text4);


        text1.setEnabled(false);
        text2.setEnabled(false);
        text3.setEnabled(false);
        text4.setEnabled(false);

        btnSave.setVisibility(View.GONE);
        btnEdit.setVisibility(View.GONE);
        btnUnassign.setVisibility(View.GONE);

        /*
        if(courseExists && courseInstructor == db.course.instructor) {
            //btnAssign.setEnabled(False)
            setTeacher
            setCourse name,date,id ect
            btnSave.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
            btnUnassign.setVisibility(View.VISIBLE);


        }
        else{
            if(courseExists && courseInstructor != db.course.instructor)
                //btnAssign.setEnabled(False)
                //btnEdit.setEnabled(False)
                //btnUnassign.setEnabled(False)
                setTeacher
                setCourse name,date,id ect


         */

        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.VISIBLE);
                btnUnassign.setVisibility(View.VISIBLE);

                //cInstructor.setText(instructorName);
                //db.setInstructor(instructorName)
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
                text2.setEnabled(true);
                text3.setEnabled(true);
                text4.setEnabled(true);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.VISIBLE);
                btnUnassign.setVisibility(View.VISIBLE);

                btnSave.setVisibility(View.GONE);
                text1.setEnabled(false);
                text2.setEnabled(false);
                text3.setEnabled(false);
                text4.setEnabled(false);

                //db.setStuff((EditText)findViewById(R.id.textName), (EditText)findViewById(R.id.text2), (EditText)findViewById(R.id.text3), (EditText)findViewById(R.id.text4) );
            }
        });

        btnUnassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.GONE);
                btnUnassign.setVisibility(View.GONE);

                //db.wipeData();
                //btnAssign.setEnabled(False)
            }
        });



    }


}