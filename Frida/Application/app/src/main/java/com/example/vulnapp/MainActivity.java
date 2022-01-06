package com.example.vulnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "VULNAPP";
    Button btn; int i = 0; boolean success;
    static String appPassword = "MyPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    boolean checkPassword(String password) {
        if(password.equals((MainActivity.appPassword))) {
            Log.i(TAG, "Password: " + password + " matched");
            return true;
        }

        else {
            Log.i(TAG, "Password: " + password + " does not match");
            return false;
        }
    }

    public void verifyUser(View view) {
        EditText editText = findViewById(R.id.inputPassword);
        String inputPassword = editText.getText().toString();
        Log.i(TAG, "Got password: " + inputPassword);
        success = checkPassword(inputPassword);

        if (success) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.example.vulnapp", "com.example.vulnapp.hiddenActivity"));
            startActivity(intent);
        }

        else {
            Toast.makeText(getApplicationContext(), "Incorrect password!", Toast.LENGTH_LONG).show();
        }
    }
}