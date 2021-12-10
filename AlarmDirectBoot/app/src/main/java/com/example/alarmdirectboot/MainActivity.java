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

public class MainActivity extends AppCompatActivity {

    static String TAG = "ALARM_DIRECT_BOOT";
    static MainActivity mainActivity;

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

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("ALARM", alarmTime);
        editor.apply();

        setAlarm();
    }

    public void setAlarm() {
        Log.i(TAG, "Setting alarm from preferences file");
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        long alarmTime = sharedPreferences.getLong("ALARM", -1);

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
        Log.i(TAG, "Alarm set for " + (alarmTime - System.currentTimeMillis()) + "ms");
        Toast.makeText(this, "Alarm set in " + (alarmTime - System.currentTimeMillis())/1000 + " seconds",Toast.LENGTH_LONG).show();

    }

    public static class BootCompletedIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                ////// reset your alarms here
                Log.i(TAG, "BOOT_COMPLETED received");
                mainActivity.setAlarm();
            }

        }
    }

}