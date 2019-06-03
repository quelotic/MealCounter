package com.quelotic.mealcounter.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final int notificationID = 101;
    private NotificationHelper notificationHelper;

    @Override
    public void onReceive(Context context, Intent intent) {

        notificationHelper = new NotificationHelper(context);

        postNotification(notificationID);

    }

    public void postNotification(int id) {
        NotificationCompat.Builder notificationBuilder = notificationHelper.getNotification("Έφαγες;", "Χόρτασες; Πήρες γεύμα τελικά;");
        if (notificationBuilder != null) {
            notificationHelper.notify(id, notificationBuilder);
        }
    }

}
