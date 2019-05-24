package com.quelotic.mealcounter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        timePicker = findViewById(R.id.timePicker);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        timePicker.setHour(sharedPref.getInt("HOUR",0));
        timePicker.setMinute(sharedPref.getInt("MIN",0));
        //timePicker.setIs24HourView(true);

    }

    // Apply button click
    public void setButton(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calendar.set(Calendar.MINUTE, timePicker.getMinute());
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
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Toast.makeText(this, "Η ώρα ειδοποίησης είναι: " + sharedPref.getInt("HOUR", 0) + ":" + sharedPref.getInt("MIN",0), Toast.LENGTH_SHORT).show();
    }
}
