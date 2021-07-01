package com.example.lab3.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.lab3.InstructorFragment1;
import com.example.lab3.InstructorFragment2;
import com.example.lab3.MainActivity3;
import com.example.lab3.R;

public class MyPagerAdapter extends SectionsPagerAdapter{
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_4, R.string.tab_text_5};
    private final Context mContext;

    public MyPagerAdapter(Context context, FragmentManager fm) {
        super(context, fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:

                Bundle args = new Bundle();
                args.putString("name", MainActivity3.Name);
                args.putString("type", "Instructor");
                fragment =new InstructorFragment1();
                fragment.setArguments(args);
                break;
            case 1:
                fragment=new InstructorFragment2();
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
        // Show 2 total pages.
        return 2;
    }

}
