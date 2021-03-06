package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class courseInfo extends AppCompatActivity {


    Spinner startFirst, timePeriodA1, startSecond, endFirst, endSecond,timePeriodA2, weekday;
    Spinner startFirst2, startSecond2,timePeriodB1, endFirst2, endSecond2, timePeriodB2, weekday2;

    String firstCourseTime, secondCourseTime, description;
    Course currentCourse;
    Button btnsave, btnExit;
    NewDBHandler db;

    String desc2, dayts;
    int cap2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        dayts="NA&NA";

        String courseName = getIntent().getStringExtra("courseN");
        String courseInstructor = getIntent().getStringExtra("TeachName");

        db = new NewDBHandler(this);
        currentCourse = db.findCourse(courseName);
        //String test = currentCourse.getInstructor();




        String[] first = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] second = new String[]{"00","15","30","45"};
        String[] timePeriod = new String[]{"AM","PM"};

        startFirst = (Spinner)findViewById(R.id.startFirst);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, first);
        startFirst.setAdapter(adapter1);

        startSecond = (Spinner)findViewById(R.id.startSecond);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, second);
        startSecond.setAdapter(adapter2);

        timePeriodA1 = (Spinner)findViewById(R.id.timePeriodA1);
        ArrayAdapter<String> timeA1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timePeriod);
        timePeriodA1.setAdapter(timeA1);

        endFirst = (Spinner)findViewById(R.id.endFirst);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, first);
        endFirst.setAdapter(adapter3);

        endSecond = (Spinner)findViewById(R.id.endSecond);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, second);
        endSecond.setAdapter(adapter4);

        timePeriodA2 = (Spinner)findViewById(R.id.timePeriodA2);
        ArrayAdapter<String> timeA2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timePeriod);
        timePeriodA2.setAdapter(timeA2);

        weekday = (Spinner)findViewById(R.id.weekday);
        String[] days = new String[]{"None","Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
        weekday.setAdapter(adapter5);




        startFirst2 = (Spinner)findViewById(R.id.startFirst2);
        startFirst2.setAdapter(adapter1);

        startSecond2 = (Spinner)findViewById(R.id.startSecond2);
        startSecond2.setAdapter(adapter2);

        timePeriodB1= (Spinner)findViewById(R.id.timePeriodB1);
        timePeriodB1.setAdapter(timeA1);

        endFirst2 = (Spinner)findViewById(R.id.endFirst2);
        endFirst2.setAdapter(adapter3);

        endSecond2 = (Spinner)findViewById(R.id.endSecond2);
        endSecond2.setAdapter(adapter4);

        timePeriodB2= (Spinner)findViewById(R.id.timePeriodB2);
        timePeriodB2.setAdapter(timeA2);

        weekday2 = (Spinner)findViewById(R.id.weekday2);
        weekday2.setAdapter(adapter5);


        btnsave = (Button)findViewById(R.id.btnSave1);
        btnExit = (Button)findViewById(R.id.btnExit);


        //Student Capacity
        EditText desc = (EditText)findViewById(R.id.editTextTextPersonName3);
        EditText cap = (EditText)findViewById(R.id.editTextNumber);


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPage(courseName, courseInstructor);
            }
        });




        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                String day1 = "NA";
                String day2 = "NA";


                if(weekday.getSelectedItem().toString() != "None"){

                    String firstPeriod = timePeriodA1.getSelectedItem().toString();
                    String secondPeriod = timePeriodA2.getSelectedItem().toString();



                    int first = Integer.parseInt(startFirst.getSelectedItem().toString());
                    int second;
                    String temp1 = startSecond.getSelectedItem().toString();
                    if(temp1 == "00"){
                        second = 0;
                    }else{
                        second = Integer.parseInt(temp1);
                    }
                    int third = Integer.parseInt(endFirst.getSelectedItem().toString());

                    int fourth;

                    String temp2 = endSecond.getSelectedItem().toString();
                    if(temp2 == "00"){
                        fourth = 0;
                    }else{
                        fourth = Integer.parseInt(temp2);
                    }





                    if(!(firstPeriod == "PM" && secondPeriod == "AM")){

                        if(!(firstPeriod == "AM" && first <= 7 && second < 30)){

                            if(!(secondPeriod == "PM" && third >= 11 && fourth > 0)){

                                if(!(firstPeriod == secondPeriod && first>=third && fourth<=second)) {
                                    firstCourseTime = "" + weekday.getSelectedItem().toString() + ": " + startFirst.getSelectedItem().toString() +":"+ startSecond.getSelectedItem().toString() + " " + timePeriodA1.getSelectedItem().toString() + "-" + endFirst.getSelectedItem().toString() + ":"+endSecond.getSelectedItem().toString() + " " + timePeriodA2.getSelectedItem().toString() + "";
                                    day1 = firstCourseTime;
                                }else{
                                    Toast.makeText(getApplicationContext(), "Start time cannot be before end time.", Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(getApplicationContext(), "End time must be before 10:00 PM.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Start time must be after 8:30 AM.", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Start time cannot be in PM if end time is in AM.", Toast.LENGTH_SHORT).show();
                    }


                }




                if(weekday2.getSelectedItem().toString() != "None"){

                    String firstPeriod = timePeriodB1.getSelectedItem().toString();
                    String secondPeriod = timePeriodB2.getSelectedItem().toString();



                    int first = Integer.parseInt(startFirst2.getSelectedItem().toString());
                    int second;
                    String temp1 = startSecond2.getSelectedItem().toString();
                    if(temp1 == "00"){
                        second = 0;
                    }else{
                        second = Integer.parseInt(temp1);
                    }
                    int third = Integer.parseInt(endFirst2.getSelectedItem().toString());

                    int fourth;

                    String temp2 = endSecond2.getSelectedItem().toString();
                    if(temp2 == "00"){
                        fourth = 0;
                    }else{
                        fourth = Integer.parseInt(temp2);
                    }





                    if(!(firstPeriod == "PM" && secondPeriod == "AM")){

                        if(!(firstPeriod == "AM" && first <= 7 && second < 30)){

                            if(!(secondPeriod == "PM" && third >= 11 && fourth > 0)){

                                if(!(firstPeriod == secondPeriod && first>=third && fourth<=second)) {
                                    secondCourseTime = "" + weekday2.getSelectedItem().toString() + ": " + startFirst2.getSelectedItem().toString() + ":"+ startSecond2.getSelectedItem().toString() + " " + timePeriodB1.getSelectedItem().toString() + "-" + endFirst2.getSelectedItem().toString() + ":" +endSecond2.getSelectedItem().toString() + " " + timePeriodB2.getSelectedItem().toString() + "";
                                    day2 = secondCourseTime;

                                }else{
                                    Toast.makeText(getApplicationContext(), "Start time cannot be before end time.", Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(getApplicationContext(), "End time must be before 10:00 PM.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Start time must be after 8:30 AM.", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Start time cannot be in PM if end time is in AM.", Toast.LENGTH_SHORT).show();
                    }
                }



                //InfoSet
                dayts = ""+day1+"&"+day2+"";

                desc2 = desc.getText().toString();


                String tempCapacity = cap.getText().toString();

                if(!tempCapacity.isEmpty()){
                    cap2 = Integer.parseInt(cap.getText().toString());
                    db.addCourseInfo(courseName, dayts, "Couple", desc2, cap2);
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter a valid number of students.", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }

    private void testPage(String courseName, String courseInstructor){
        Intent intent = new Intent (this, IntructorCoursePage.class);

        intent.putExtra("instructorName", courseInstructor);
        intent.putExtra("courseName", courseName);

        startActivity(intent);
    }

}

