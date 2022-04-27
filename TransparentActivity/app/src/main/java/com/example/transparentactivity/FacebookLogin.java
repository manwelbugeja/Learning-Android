package com.example.transparentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class FacebookLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        moveTaskToBack(true);
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this, "Session Expired: Please log in again", Toast.LENGTH_LONG).show();
        super.onRestart();
    }

    public void loginButton(View view) {
        Toast.makeText(this, "Log in successful", Toast.LENGTH_LONG).show();

        finish();
    }
}