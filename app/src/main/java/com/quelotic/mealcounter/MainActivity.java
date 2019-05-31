package com.quelotic.mealcounter;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private NavigationTabStrip navigationTabStrip;
    private TimePicker timePicker;
    final static String filename = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initUI();
        setUI();
    }

    private void initUI() {
        viewPager = findViewById(R.id.viewPager);
        navigationTabStrip = findViewById(R.id.navTabStrip);
        //timePicker = findViewById(R.id.timePicker);
    }

    private void setUI() {

        // TIME PICKER
        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //timePicker.setHour(sharedPref.getInt("HOUR", 0));
        //timePicker.setMinute(sharedPref.getInt("MIN", 0));

        // PAGER
        viewPager.setAdapter(new com.quelotic.mealcounter.PagerAdapter(getSupportFragmentManager()));
        navigationTabStrip.setTitles("ΑΡΧΙΚΗ", "ΡΥΘΜΙΣΗ", "ΙΣΤΟΡΙΚΟ");
        navigationTabStrip.setTabIndex(0, true);
        navigationTabStrip.setStripType(NavigationTabStrip.StripType.POINT);
        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.TOP);
        navigationTabStrip.setViewPager(viewPager, 0);
        //navigationTabStrip.setTypeface("fonts/typeface.ttf");
        navigationTabStrip.setAnimationDuration(300);
        //navigationTabStrip.setOnPageChangeListener(...);
        //navigationTabStrip.setOnTabStripSelectedIndexListener(...);
    }

    // Apply button click
    public void setButton(View view) {

        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());
        if (calendar.before(now)) calendar.add(Calendar.DAY_OF_MONTH, 1);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Εφαρμόστηκε ώρα ειδοποίησης: " + calendar.getTime(), Toast.LENGTH_SHORT).show();

        // Save preference
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("HOUR", timePicker.getHour());
        editor.putInt("MIN", timePicker.getMinute());
        editor.apply();

    }

    public void checkButton(View view) {

        try {
            int plates = 0;
            File file = new File(getFilesDir(), filename);

            if (file.exists()) {
                FileInputStream fileInputStream = openFileInput(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                while (bufferedReader.readLine() != null) plates++;
                bufferedReader.close();
            }

            Toast.makeText(getBaseContext(), "Έχεις φάει " + plates + " γεύματα! " + (30 - plates) + " ακόμη διαθέσιμα!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Πρόβλημα!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void logButton(View view) {
        LogDialog logDialog = new LogDialog();
        logDialog.show(getSupportFragmentManager(), "log_dialog");
    }

}