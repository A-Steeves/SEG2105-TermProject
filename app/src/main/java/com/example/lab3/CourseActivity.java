package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CourseActivity extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.edit_course);
        Button delete = findViewById(R.id.delete_course);
        Button save = findViewById(R.id.save_course);
        Intent intent = this.getIntent();
        NewDBHandler myDb = new NewDBHandler(CourseActivity.this);

        //if(intent!=null){
        String name = intent.getStringExtra("CourseName");
        String code = intent.getStringExtra("CourseCode");
        TextView coName = findViewById(R.id.name);
        TextView coCode = findViewById(R.id.textView7);
        coName.setText(name);
        coCode.setText(code);
        //}

       delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                myDb.deleteCourse(name);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {
                TextView coName = findViewById(R.id.name);
                TextView coCode = findViewById(R.id.textView7);
                String newName = coName.getText().toString();
                String newCode = coCode.getText().toString();
                /*myDb.deleteCourse(name);
                myDb.addCourse(newCode, newName);*/
               // myDb.editCourse(name, newCode, newName);
            }
        });

    }
}
