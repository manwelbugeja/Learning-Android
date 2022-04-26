package com.example.transparentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        Intent i = new Intent(this, TransparentActivity.class);
        startActivity(i);

        super.onDestroy();
    }

    public void startActivity(View view) {
        Intent i = new Intent(this, TransparentActivity.class);
        startActivity(i);
    }
}