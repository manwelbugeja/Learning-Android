package com.example.alarmdirectboot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    static String TAG = "ALARM_DIRECT_BOOT";
    String fileName = "alarmsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void setNewAlarmData(View view) {
        TextView alarmTimeTextView = findViewById(R.id.setTime);
        String alarmTimeString = alarmTimeTextView.getText().toString();
        Log.i(TAG, "Got: " + alarmTimeString);

        long alarmTime = System.currentTimeMillis() + (Integer.parseInt(alarmTimeString) * 1000L);

        //Context directBootContext = getApplicationContext().createDeviceProtectedStorageContext();
        //SharedPreferences sharedPreferences = directBootContext.getSharedPreferences(fileName, MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("ALARM", alarmTime);
        editor.apply();
        Log.i(TAG, "Wrote alarm to preferences file");

        Intent serviceIntent = new Intent(this, MyService.class);
        Log.i(TAG, "Starting service");
        startService(serviceIntent);
    }


    public static class DirectBootReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if ("android.intent.action.LOCKED_BOOT_COMPLETED".equals(intent.getAction())) {
                ////// reset your alarms here
                Log.i(TAG, "LOCKED_BOOT_COMPLETED received");
                Log.i(TAG, "Starting service");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(new Intent(context, MyService.class));
                } else {
                    context.startService(new Intent(context, MyService.class));
                }
            }

        }
    }

}