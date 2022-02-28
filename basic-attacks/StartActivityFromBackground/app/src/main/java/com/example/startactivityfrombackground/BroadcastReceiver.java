package com.example.startactivityfrombackground;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MainActivity.TAG, "Broadcast received");

        // External Activity must be exported in manifest!
        Intent ActivityIntent = new Intent();
        String pkg = "com.example.startactivityfrombackground";
        String cls = "com.example.startactivityfrombackground.MyActivity";

        // Can also be external activity
        //  String pkg = "com.example.insecureloginapp";
        //  String cls = "com.example.insecureloginapp.HiddenActivity";

        ActivityIntent.setComponent(new ComponentName(pkg, cls));
        context.startActivity(ActivityIntent);
    }
}
