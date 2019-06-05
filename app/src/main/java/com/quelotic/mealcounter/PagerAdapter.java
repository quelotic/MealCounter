package com.quelotic.mealcounter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.quelotic.mealcounter.fragments.MainFragment;
import com.quelotic.mealcounter.fragments.ClockFragment;
import com.quelotic.mealcounter.fragments.LogFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages.
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new ClockFragment();
            case 2:
                return new LogFragment();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        //return "Tab " + position;
        switch (position) {
            case 0:
                return "ΑΡΧΙΚΗ";
            case 1:
                return "ΡΥΘΜΙΣΗ";
            case 2:
                return "ΙΣΤΟΡΙΚΟ";
            default:
                return "unknown";
        }
    }
}