package com.example.lab3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class StudentFragment3 extends Fragment {

    private ArrayList<Course> courseList;
    ListView listView;
    FloatingActionButton all;
    ImageButton searchCourse;
    ImageButton searchCode;
    ImageButton searchDay;
    Spinner weekday;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_student3, container, false);

        //find = v.findViewById(R.id.Find3);

        searchCourse = v.findViewById(R.id.imageButtonB);
        searchCode = v.findViewById(R.id.imageButton2B);
        searchDay = v.findViewById(R.id.imageButton2C);

        all = v.findViewById(R.id.floatingActionButtonB);
        listView = v.findViewById(R.id.listview3B);
        courseList = new ArrayList<Course>();
        NewDBHandler myDb = new NewDBHandler( getActivity() );
        courseList = myDb.validCourses();

        weekday = (Spinner)v.findViewById(R.id.endFirst2C);
        String[] days = new String[]{"Select a Day ▼","Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, days);
        weekday.setAdapter(adapter1);

        courseListAdapter adapter = new courseListAdapter(getActivity(), R.layout.course_adapter_view_layout, courseList);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        ActivityResultLauncher<Intent> activityClosed = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        courseList.clear();
                        courseList.addAll(myDb.validCourses());
                        adapter.notifyDataSetChanged();
                    }
                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Course selected = courseList.get(position);
                String courseName = selected.getName();
                String courseCode = selected.getCode();
                String courseInstructor = selected.getInstructor();

                String tempInstructor = getActivity().getIntent().getStringExtra("name");



                Intent intent = new Intent(getActivity(), studentCoursePage.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseID", courseCode);
                intent.putExtra("instructorName", tempInstructor);


                //based on item add info to intent
                //startActivity(intent);
                activityClosed.launch(intent);
            }
        });



        searchCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                TextView nameIn = v.findViewById(R.id.CourseName1B);
                String name = nameIn.getText().toString();
                Course found = myDb.findCourse(name);
                Boolean courseCheck = validateCourseName();
                if (courseCheck != false) {
                    if (found != null) {
                        courseList.clear();
                        courseList.add(found);
                        adapter.notifyDataSetChanged();
                    }else{
                        nameIn.setError("Your search yielded no results");
                    }
                }
            }
        });

        searchCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                TextView codeIn = v.findViewById(R.id.CourseCode1B);
                String code = codeIn.getText().toString();
                Course foundbycode = myDb.findCourseByCode(code);
                Boolean courseCheck = validateCourseCode();
                if (courseCheck != false) {
                    if (foundbycode !=null) {
                        courseList.clear();
                        courseList.add(foundbycode);
                        adapter.notifyDataSetChanged();
                    }else{
                        codeIn.setError("Your search yielded no results");
                    }
                }
            }
        });

        searchDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {

                Spinner dayIn = v.findViewById(R.id.endFirst2C);
                //String day = dayIn.getText().toString();
                String day = weekday.getSelectedItem().toString();

                if (day.equals("Select a Day ▼")) {
                    TextView errorText = (TextView)weekday.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Please select a day");//changes the selected item text to this
                } else {
                    courseList.clear();
                    courseList.addAll(myDb.findCourseByDay(day));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                courseList.clear();
                courseList.addAll(myDb.validCourses());
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    private boolean validateCourseName() {
        TextView nameIn = getActivity().findViewById(R.id.CourseName1B);
        String name = nameIn.getText().toString();
        if (name.isEmpty() == true) {
            nameIn.setError("This field cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateCourseCode() {
        TextView codeIn = getActivity().findViewById(R.id.CourseCode1B);
        String code = codeIn.getText().toString();
        if (code.isEmpty() == true) {
            codeIn.setError("This field cannot be empty");
            return false;
        } else if (!Pattern.matches("\\b[A-Z]{3}\\d{4}\\b", code)) {
            codeIn.setError("Please enter a valid course code");
            return false;
        }
        return true;
    }

}