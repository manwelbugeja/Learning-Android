package com.example.startactivityfrombackground;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BackgroundService extends Service {
    public BackgroundService() {
        Log.i(MainActivity.TAG, "Background service started");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(MainActivity.TAG, "Service started with context");
        Intent broadcastIntent = new Intent("android.intent.action.CUSTOM_INTENT");
        Log.i(MainActivity.TAG, "Sending broadcast soon");
        sendBroadcast(broadcastIntent);
        Log.i(MainActivity.TAG, "Broadcast sent");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}