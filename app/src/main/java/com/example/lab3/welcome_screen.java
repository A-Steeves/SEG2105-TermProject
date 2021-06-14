package com.example.lab3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.lab3.databinding.ActivityWelcomeScreenBinding;

public class welcome_screen extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityWelcomeScreenBinding binding;
    private TextView textName;
    private TextView textPass;
    private TextView textType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_welcome_screen);
        String name = getIntent().getStringExtra("name");

        String type = getIntent().getStringExtra("type");

        textName = (TextView)findViewById(R.id.textView2);

        textType = (TextView)findViewById(R.id.cName);

        textName.setText("User Name: "+name);

        textType.setText("User Type: "+type);



    }


}