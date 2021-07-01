package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
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


public class InstructorFragment2 extends Fragment {

    private ArrayList<Course> courseList;
    ListView listView;
    FloatingActionButton all;
    ImageButton searchCourse;
    ImageButton searchCode;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_instructor2, container, false);

        //find = v.findViewById(R.id.Find3);
        searchCourse = v.findViewById(R.id.imageButton);
        searchCode = v.findViewById(R.id.imageButton2);
        all = v.findViewById(R.id.floatingActionButton);
        listView = v.findViewById(R.id.listview3);
        courseList = new ArrayList<Course>();
        NewDBHandler myDb = new NewDBHandler( getActivity() );
        courseList = myDb.allCourses();

        courseListAdapter adapter = new courseListAdapter(getActivity(), R.layout.course_adapter_view_layout, courseList);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        ActivityResultLauncher<Intent> activityClosed = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        courseList.clear();
                        courseList.addAll(myDb.allCourses());
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
                String courseInstructor = selected.getInstructor();;

                Intent intent = new Intent(getActivity(), IntructorCoursePage.class);
                intent.putExtra("courseName", courseName);
                intent.putExtra("courseID", courseCode);
                intent.putExtra("instructorName", courseInstructor);


                //based on item add info to intent
                //startActivity(intent);
                activityClosed.launch(intent);
            }
        });



       searchCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                TextView nameIn = v.findViewById(R.id.CourseName1);
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
                TextView codeIn = v.findViewById(R.id.CourseCode1);
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

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                courseList.clear();
                courseList.addAll(myDb.allCourses());
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    private boolean validateCourseName() {
        TextView nameIn = getActivity().findViewById(R.id.CourseName1);
        String name = nameIn.getText().toString();
        if (name.isEmpty() == true) {
            nameIn.setError("This field cannot be empty");
            return false;
        }
        return true;
    }

   private boolean validateCourseCode() {
        TextView codeIn = getActivity().findViewById(R.id.CourseCode1);
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