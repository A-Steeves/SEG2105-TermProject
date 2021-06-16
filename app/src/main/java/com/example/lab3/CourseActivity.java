package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.regex.Pattern;


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
               boolean course = validateCourseCode();
               boolean code = validateCourseName();
               if(course!=false && code!=false) {
                   myDb.editCourse(name, newCode, newName);
                   Toast.makeText(CourseActivity.this, "Change Saved",
                           Toast.LENGTH_LONG).show();
               }
           }
       });
    }

    public void onBackPressed(){
        setResult(RESULT_OK);
        finish();
    }

    private boolean validateCourseName() {
        TextView nameIn = findViewById(R.id.name);
        String name = nameIn.getText().toString();
        if (name.isEmpty() == true) {
            nameIn.setError("This field cannot be empty");
            return false;
        }
        return true;
    }

    private boolean validateCourseCode() {
        TextView codeIn = findViewById(R.id.textView7);
        String code = codeIn.getText().toString();
        if (code.isEmpty() == true) {
            codeIn.setError("This field cannot be empty");
            return false;
        } else if (!Pattern.matches("\\b[A-Z]{3}\\d{3}\\b", code)) {
            codeIn.setError("Please enter a valid course code");
            return false;
        }
        return true;
    }

}
