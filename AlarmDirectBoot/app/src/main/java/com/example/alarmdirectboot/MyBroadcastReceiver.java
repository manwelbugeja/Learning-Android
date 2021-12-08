package com.example.alarmdirectboot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {
    String TAG = "AlARM_DIRECT_BOOT";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG, "Alarm now");
        Toast.makeText(context, "Alarm now", Toast.LENGTH_LONG).show();
//        showNotification(context);
        MediaPlayer mp= MediaPlayer.create(context, R.raw.alarm);
        mp.start();

    }

//    private void showNotification(Context context) {
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
//                new Intent(context, MainActivity.class), 0);
//
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(context)
//                        .setSmallIcon(0)
//                        .setContentTitle("My notification")
//                        .setContentText("Hello World!");
//        mBuilder.setContentIntent(contentIntent);
//        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//        mBuilder.setAutoCancel(true);
//        NotificationManager mNotificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(1, mBuilder.build());
//
//    }


}