package com.example.lab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdminFragment2 extends Fragment {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //recyclerView = recyclerView.findViewById(R.id.recycle);
        //listView = listView.findViewById(R.id.listview);
        View v = inflater.inflate(R.layout.fragment_admin2,container,false);
        //View v = inflater.inflate(R.layout.list_items,container,false);

        //setAdapter();
        return v;
    }

   /* private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(courseList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext());
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }*/
}
