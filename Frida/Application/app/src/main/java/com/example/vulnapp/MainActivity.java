package com.example.vulnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn; int i = 0; boolean success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btnSubmit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("VALUE", "Value is " + i);
                success = setOutput(i);
                if (success) {
                    Toast.makeText(getApplicationContext(), "Cracked", Toast.LENGTH_LONG).show();
                    Log.i("VALUE", "Value in if is " + i);
                } else {
                    Toast.makeText(getApplicationContext(), "Can't crack it", Toast.LENGTH_LONG).show();
                    Log.i("VALUE", "Value in else case is " + i);
                }
            }
        });
    }

    boolean setOutput(int i) {
        if (i==1)
            return true;
        else
            return false;
    }

}