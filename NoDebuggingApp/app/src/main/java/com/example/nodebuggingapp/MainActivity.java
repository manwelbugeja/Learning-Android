package com.example.nodebuggingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isDebuggable(this)) {
            finish();
        }
    }

    public static boolean isDebuggable(Context context){
        return (context.getApplicationInfo().flags & ApplicationInfo.
                FLAG_DEBUGGABLE) != 0;
    }

}