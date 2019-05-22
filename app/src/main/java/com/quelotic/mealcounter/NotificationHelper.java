package com.quelotic.mealcounter;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

class NotificationHelper extends ContextWrapper {

    private NotificationManager notificationManager;
    public static final String CHANNEL_ID = "com.quelotic.mealcounter.channel";
    public static final String CHANNEL_NAME = "Subscription Meal Counter";

    public NotificationHelper(Context base) {
        super(base);
        createChannel();
    }

    // Create notification channel
    public void createChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        //notificationChannel.enableLights(true);
        //notificationChannel.setLightColor(Color.RED);
        //notificationChannel.enableVibration(true);
        notificationChannel.setShowBadge(true);
        //notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(notificationChannel);
    }

    //Create the notification
    public NotificationCompat.Builder getNotification(String title, String body) {

        String yes = "update";
        String no = "noUpdate";

        Intent yesBroadcastIntent = new Intent(this, NotificationReceiver.class);
        yesBroadcastIntent.setAction(NotificationReceiver.YESACTION);
        PendingIntent yesActionIntent = PendingIntent.getBroadcast(this,
                0, yesBroadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent noBroadcastIntent = new Intent(this, NotificationReceiver.class);
        //noBroadcastIntent.putExtra("negative", no);
        noBroadcastIntent.setAction(NotificationReceiver.NOACTION);
        PendingIntent noActionIntent = PendingIntent.getBroadcast(this,
                0, noBroadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.food)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Ναι", yesActionIntent)
                .addAction(R.mipmap.ic_launcher, "Όχι", noActionIntent);
    }

    public void notify(int id, NotificationCompat.Builder notification) {
        getManager().notify(id, notification.build());
    }

    //Send notification to the NotificationManager system service
    private NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
}
