package com.example.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView myWebView = (WebView) findViewById(R.id.webview);

        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        // opens new links in the webview instead of browser
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("wikipedia")) {

                    return super.shouldOverrideUrlLoading(view, request);
                }else{
                    try {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(request.getUrl())));
                        startActivity(browserIntent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:alert('Hello World');");
            }
        });

        String url = getIntent().getStringExtra("url");
        Log.i(MainActivity.TAG, "Got Data: " + url);

        // Load URL
        if (url!=null) {
            // only load content that contains "wikipedia" in WebView
            if (url.contains("wikipedia")) {
                myWebView.loadUrl(url);
            }
            else {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else {
            myWebView.loadUrl("https://www.wikipedia.com");
        }
    }
}
