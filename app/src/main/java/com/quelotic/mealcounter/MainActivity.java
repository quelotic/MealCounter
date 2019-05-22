package com.quelotic.mealcounter;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private static final int notificationID = 101;
    private TimePicker timePicker;
    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        notificationHelper = new NotificationHelper(this);

    }

    //Post the notifications
    public void postNotification(int id) {
        NotificationCompat.Builder notificationBuilder = notificationHelper.getNotification(getString(R.string.app_name), getString(R.string.notification_body));
        if (notificationBuilder != null) {
            notificationHelper.notify(id, notificationBuilder);
        }
    }

    // Button click
    public void setButton(View view) {
        postNotification(notificationID);
    }
}
