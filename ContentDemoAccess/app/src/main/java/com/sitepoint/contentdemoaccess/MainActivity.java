package com.sitepoint.contentdemoaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Uri imageProviderURI = null;
    ContentProviderClient imageProviderContentResolver = null;
    Cursor imageProviderCursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            imageProviderURI = Uri.parse("content://sitepoint.contentdemo.images/images");
            imageProviderContentResolver = getContentResolver().acquireContentProviderClient(imageProviderURI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onRetrieveImageButton(View view) {
        TextView outputText = findViewById(R.id.output_text);

        if (imageProviderContentResolver != null) {
            //imageProviderCursor = imageProviderContentResolver.query
            outputText.setText("Button clicked");
        } else {
            outputText.setText("Cursor not found");
        }
    }
}