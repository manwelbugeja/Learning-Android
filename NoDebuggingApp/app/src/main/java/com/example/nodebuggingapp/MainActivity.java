package com.example.nodebuggingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static boolean isDebuggable(Context context){
        return (context.getApplicationInfo().flags & ApplicationInfo.
                FLAG_DEBUGGABLE) != 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isDebuggable(this)) {
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}