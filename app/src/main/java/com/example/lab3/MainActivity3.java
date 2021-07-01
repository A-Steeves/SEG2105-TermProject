package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.lab3.databinding.ActivityMain3Binding;
import com.example.lab3.ui.main.MyPagerAdapter;
import com.example.lab3.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        String name = intent.getStringExtra("name");
        String type = intent.getStringExtra("type");
        Intent intente = new Intent(this, MyPagerAdapter.class);
        intente.putExtra("name", name);
        intente.putExtra("type", type);


        /*Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("type", type);*/

        SectionsPagerAdapter MyPagerAdapter = new MyPagerAdapter(this, getSupportFragmentManager());


        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(MyPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }

}
