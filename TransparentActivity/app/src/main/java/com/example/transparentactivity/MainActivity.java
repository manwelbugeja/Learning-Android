package com.example.transparentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "TRANSPARENT_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivity(View view) {
        Intent i = new Intent(this, FacebookLogin.class);
        startActivity(i);
    }
}