package com.example.lab3.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.lab3.R;
import com.example.lab3.StudentFragment1;
import com.example.lab3.StudentFragment2;
import com.example.lab3.StudentFragment3;
import com.example.lab3.StudentTab;

public class StudentPagerAdapter extends SectionsPagerAdapter{
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_6, R.string.tab_text_7, R.string.tab_text_8};
    private final Context mContext;

    public StudentPagerAdapter(Context context, FragmentManager fm) {
        super(context, fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                Bundle args = new Bundle();
                args.putString("name", StudentTab.Name);
                args.putString("type", "Student");
                fragment =new StudentFragment1();
                fragment.setArguments(args);
                break;
            case 1:
                Bundle argss = new Bundle();
                argss.putString("name", StudentTab.Name);
                argss.putString("type", "Student");
                fragment=new StudentFragment2();
                fragment.setArguments(argss);
                break;
            case 2:
                Bundle arggs = new Bundle();
                arggs.putString("name", StudentTab.Name);
                arggs.putString("type", "Student");
                fragment=new StudentFragment3();
                fragment.setArguments(arggs);
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

}
