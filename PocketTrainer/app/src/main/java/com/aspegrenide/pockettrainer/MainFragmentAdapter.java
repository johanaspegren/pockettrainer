package com.aspegrenide.pockettrainer;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aspegrenide.pockettrainer.data.Exercise;
import com.aspegrenide.pockettrainer.data.Gren;

import java.util.ArrayList;

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    ArrayList<Gren> allGrenar;
    ArrayList<Exercise> allExercises;

    public MainFragmentAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GrenarFragment grenatFragment = new GrenarFragment();
                return grenatFragment;
            case 1:
                SessionFragment sessionFragment = new SessionFragment();
                return sessionFragment;
            case 2:
                HistoryFragment historyFragment = new HistoryFragment();
                return historyFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}