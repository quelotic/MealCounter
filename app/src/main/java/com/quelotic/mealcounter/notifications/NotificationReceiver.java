package com.quelotic.mealcounter.notifications;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class NotificationReceiver extends BroadcastReceiver {

    public static final String YES = "YES";
    public static final String NO = "NO";
    final static String filename = "data";

    @Override
    public void onReceive(final Context context, Intent intent) {

        String notificationService = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(notificationService);

        final String action = intent.getAction();
        Handler mHandler = new Handler(Looper.getMainLooper());

        if (YES.equals(action)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    Calendar now = Calendar.getInstance();
                    String fileContents = now.toString() + ",1" + "\n\r";

                    try {
                        FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_APPEND | Context.MODE_PRIVATE);
                        fileOutputStream.write(fileContents.getBytes());
                        fileOutputStream.close();
                        Toast.makeText(context,"Το σημερινό γεύμα προσθέθηκε επιτυχώς!",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e){
                        Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(context, "I/O Exception", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        } else if (NO.equals(action)) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                }
            });
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }

        notificationManager.cancel(101);
    }
}