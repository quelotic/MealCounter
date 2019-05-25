package com.quelotic.mealcounter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Meal> meals = new ArrayList<>();

    private TimePicker timePicker;
    private RecyclerView recyclerView;
    private MealsAdapter mAdapter;
    protected LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        timePicker = findViewById(R.id.timePicker);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        timePicker.setHour(sharedPref.getInt("HOUR", 0));
        timePicker.setMinute(sharedPref.getInt("MIN", 0));

        recyclerView = findViewById(R.id.rview);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mAdapter = new MealsAdapter(meals);
        recyclerView.setAdapter(mAdapter);

        populateMealsDetails();
        mAdapter.notifyDataSetChanged();

    }

    private void populateMealsDetails() {

        InputStream inputStream = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );

        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                // Split by commas
                String[] tokens = line.split(",");
                // Read the data
                Meal meal = new Meal();
                meal.setId(Integer.parseInt(tokens[0]));
                meal.setDate(tokens[1]);
                meal.setPlates(Integer.parseInt(tokens[2]));
                meals.add(meal);
            }
        } catch (IOException e) {
            Log.wtf("MealCounter", "Error reading data file on line: " + line, e);
            e.printStackTrace();
        }
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

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Toast.makeText(this, "Η ώρα ειδοποίησης είναι: " + sharedPref.getInt("HOUR", 0) + ":" + sharedPref.getInt("MIN", 0), Toast.LENGTH_SHORT).show();

    }
}
