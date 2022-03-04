package com.example.insecureserviceapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MainActivity.TAG, "Broadcast received");

        // External Activity must be exported in manifest!
        Intent ServiceIntent = new Intent(context, MyService.class);
        context.startService(ServiceIntent);
    }
}
