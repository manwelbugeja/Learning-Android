package com.example.startexternalactivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class StartExternalActivity extends Service {
    public StartExternalActivity() {
        Log.i(MainActivity.TAG, "Service started");
        Intent externalActivityIntent = new Intent();
        String pkg = "com.example.insecureloginapp";
        String cls = "com.example.insecureloginapp.HiddenActivity";
        pkg = "com.example.startexternalactivity";
        cls = "com.example.startexternalactivity.MainActivity";
        externalActivityIntent.setComponent(new ComponentName(pkg, cls));
        startActivity(externalActivityIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}