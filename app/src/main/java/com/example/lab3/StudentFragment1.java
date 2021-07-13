package com.example.lab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

    public class StudentFragment1 extends Fragment {
        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.student_welcome  ,container,false);
            Bundle bundle = this.getArguments();
            String name = bundle.getString("name");
            String type = bundle.getString("type");
            TextView textName = v.findViewById(R.id.textView2A);
            TextView textType = v.findViewById(R.id.cNameA);
            textName.setText("User Name: "+name);
            textType.setText("User Type: "+type);
            return v;

        }
    }


