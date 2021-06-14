package com.example.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class AdminFragment3 extends Fragment{
    private ArrayList<Course> courseList;
    ListView listView;
    Activity context;
    Button add;
    Button find;
    Button all;
    Button delete;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin3,container,false);

        add = v.findViewById(R.id.Add);
        find = v.findViewById(R.id.Find2);
        all = v.findViewById(R.id.All2);
        listView = v.findViewById(R.id.listview);
        courseList = new ArrayList<Course>();
        NewDBHandler myDb = new NewDBHandler(getActivity());
        courseList = myDb.allCourses();

        courseListAdapter adapter = new courseListAdapter(getActivity(), R.layout.adapter_view_layout, courseList);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapter,View view, int position, long id) {
                Course selected = courseList.get(position);
                String courseName = selected.getName();
                String courseCode = selected.getCode();

                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("CourseName", courseName);
                intent.putExtra("CourseCode", courseCode);

                //based on item add info to intent
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                TextView nameIn = v.findViewById(R.id.CourseName);
                String name = nameIn.getText().toString();
                TextView codeIn = v.findViewById(R.id.CourseCode);
                String code = codeIn.getText().toString();
                myDb.addCourse(code, name);
                courseList.clear();
                courseList.addAll(myDb.allCourses());
                adapter.notifyDataSetChanged();
                nameIn.setText("Course Name");
                codeIn.setText("Course Code");
                System.out.println(name + "hi3");
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                TextView nameIn = v.findViewById(R.id.CourseName);
                String name = nameIn.getText().toString();
                Course found = myDb.findCourse(name);
                if(found != null) {
                    courseList.clear();
                    courseList.add(found);
                    adapter.notifyDataSetChanged();
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


}
