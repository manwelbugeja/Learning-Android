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
        MediaPlayer mp= MediaPlayer.create(context, R.raw.bell_sound);
        mp.start();

    }
}