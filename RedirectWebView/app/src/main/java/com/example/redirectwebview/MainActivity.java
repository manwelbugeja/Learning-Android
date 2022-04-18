package com.example.redirectwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public String TAG = "REDIRECT_WEBVIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void ButtonStartClick(View view) {
        Log.i(TAG, "Button clicked");

        String url = null;

        // Correct usage
        url = "https://en.wikipedia.org/wiki/Android_(operating_system)";

        // Opens in web browser (as should)
        //url = "https://www.google.com";

        // Bug: Opens in WebView !! (since it contains wikipedia in URL)
        //url = "https://stackoverflow.blog/2011/01/05/the-wikipedia-of-long-tail-programming-questions/";

        Intent i = new Intent("com.example.webvieweample.VIEW");
        i.putExtra("url", url);
        sendBroadcast(i);
        Log.i(TAG, "Sent Intent");

    }
}