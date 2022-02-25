package com.example.insecureloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button loginButton;
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginButton);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }


    public void loginButtonClick(View view) {
        if ( usernameEditText.getText().toString().equals("username")
            && passwordEditText.getText().toString().equals("password") ) {
            Intent hiddenActivityIntent = new Intent(this, HiddenActivity.class);
            this.startActivity(hiddenActivityIntent);
        }

        else {
            Toast.makeText(getApplicationContext(), "Incorrect deets, try again!", Toast.LENGTH_SHORT).show();
        }
    }
}