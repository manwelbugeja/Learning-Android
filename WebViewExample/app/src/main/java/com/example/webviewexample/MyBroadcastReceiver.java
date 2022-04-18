package com.example.webviewexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MainActivity.TAG, "Broadcast received");
        String url = intent.getStringExtra("url");
        Log.i(MainActivity.TAG, "Got: " + url);

        Intent i = new Intent(context, WebViewActivity.class);
        i.putExtra("url", url);
        context.startActivity(i);
    }
}
