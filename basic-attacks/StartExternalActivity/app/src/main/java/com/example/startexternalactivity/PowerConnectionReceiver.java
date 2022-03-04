package com.example.startexternalactivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PowerConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MainActivity.TAG, "Charging broadcast received");

        if(intent.getAction() == Intent.ACTION_POWER_CONNECTED) {
            Log.i(MainActivity.TAG, "Power connected");

            // Start background service to take screenshot
            Intent screenShotService = new Intent(context, ScreenGrabCurrentView.class);
            context.startService(screenShotService);

            Log.i(MainActivity.TAG, "Starting activity");
            // External Activity must be exported in manifest!
            Intent externalActivityIntent = new Intent();
            String pkg = "com.example.insecureloginapp";
            String cls = "com.example.insecureloginapp.HiddenActivity";
            externalActivityIntent.setComponent(new ComponentName(pkg, cls));
            context.startActivity(externalActivityIntent);

        } else if(intent.getAction() == Intent.ACTION_POWER_DISCONNECTED){
            //Handle power disconnected
        }
    }
}
