package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.lab3.databinding.ActivityStudentTabBinding;
import com.example.lab3.ui.main.SectionsPagerAdapter;
import com.example.lab3.ui.main.StudentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class StudentTab extends AppCompatActivity {

    private ActivityStudentTabBinding binding;
    public static String Name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStudentTabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter StudentPagerAdapter = new StudentPagerAdapter(this, getSupportFragmentManager());

        Intent intent = this.getIntent();
        String name = intent.getStringExtra("name");


        String type = intent.getStringExtra("type");


        Intent intente = new Intent(this, StudentPagerAdapter.class);
        intente.putExtra("name", name);
        intente.putExtra("type", type);
        Name(name);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(StudentPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }

    void Name(String name){
        Name = name;
    }
}