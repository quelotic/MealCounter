package com.quelotic.mealcounter;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class NotificationReceiver extends BroadcastReceiver {

    public static final String YESACTION = "YESACTION";
    public static final String NOACTION = "NOACTION";

    @Override
    public void onReceive(final Context context, Intent intent) {

        String notificationService = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(notificationService);

        final String action = intent.getAction();
        Handler mHandler = new Handler(Looper.getMainLooper());

        if (YESACTION.equals(action)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "OLEEE", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (NOACTION.equals(action)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "NOPE", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }

        notificationManager.cancel(101);
    }
}