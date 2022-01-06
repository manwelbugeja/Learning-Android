package com.example.vulnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class hiddenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);
    }
}