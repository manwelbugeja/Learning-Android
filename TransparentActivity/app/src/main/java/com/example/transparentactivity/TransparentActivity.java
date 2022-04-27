package com.example.transparentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;

public class TransparentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
        moveTaskToBack(true);
    }

    @Override
    protected void onResume() {
        Intent i = new Intent(this, FacebookLogin.class);
        startActivity(i);
        super.onResume();
    }
}