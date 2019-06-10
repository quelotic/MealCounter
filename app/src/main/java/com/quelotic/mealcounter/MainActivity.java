package com.quelotic.mealcounter;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        viewPager = findViewById(R.id.viewPager);
        viewPagerTab = findViewById(R.id.tabs);

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPagerTab.setViewPager(viewPager);

    }
}