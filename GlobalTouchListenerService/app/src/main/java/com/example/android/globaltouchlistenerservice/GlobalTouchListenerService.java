package com.example.android.globaltouchlistenerservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class GlobalTouchListenerService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    protected void onServiceConnected() {
        Log.i("LISTENER_SERVICE", "hello world");
        setLayoutOverlay();
        startOnTouchListener();
    }

    FrameLayout fullscreenOverlayLayout;

    void setLayoutOverlay() {
        // Create an overlay and display the action bar
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        fullscreenOverlayLayout = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        //lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //lp.gravity = Gravity.RIGHT;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.full_screen_overlay_layout, fullscreenOverlayLayout);
        wm.addView(fullscreenOverlayLayout, lp);
        Log.i("LISTENER_SERVICE", "Layout set");
    }
    int x = 0, y = 0;

    @SuppressLint("ClickableViewAccessibility")
    void startOnTouchListener() {
        Log.i("LISTENER_SERVICE", "HERE");
        Button tapLocationButton = (Button) fullscreenOverlayLayout.findViewById(R.id.tap_location);
// BUG run_twice: runs twice
        tapLocationButton.setOnTouchListener(new View.OnTouchListener() {
            //@SuppressLint("ClickableViewAccessibility")
            //@RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                getTapCoordinates(v, event);
                //removeLayoutOverlay();
                //dispatchGesture(createClick(x, y), null, null);
                new SendTouch().execute();

                return false;
            }
        });
    }

    void getTapCoordinates(View v, MotionEvent event) {
        int oldX = x;
        int oldY = y;

        x = (int)event.getX();
        y = (int)event.getY();

        // temp code to fix bug run_twice
        if (x == 0 && y ==  0) {
            x = oldX;
            y = oldY;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }

        Log.i("LISTENER_SERVICE", "GOT: " + x + ", " + y);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static GestureDescription createClick(float x, float y) {
        Log.i("LISTENER_SERVICE", "DISPATCHING: " + x + ", " + y);

        // for a single tap a duration of 1 ms is enough
        final int DURATION = 1;

        Path clickPath = new Path();
        clickPath.moveTo(x, y);
        GestureDescription.StrokeDescription clickStroke =
                new GestureDescription.StrokeDescription(clickPath, 0, DURATION);
        GestureDescription.Builder clickBuilder = new GestureDescription.Builder();
        clickBuilder.addStroke(clickStroke);
        return clickBuilder.build();
    }

    void removeLayoutOverlay() {
        Log.i("LISTENER_SERVICE", "Removing layout overlay");
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.removeView(fullscreenOverlayLayout);
    }

    private class SendTouch extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            removeLayoutOverlay();
            return null;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(Void result) {
            dispatchGesture(createClick(x, y), null, null);
            setLayoutOverlay();
            startOnTouchListener();
        }
    }

}
