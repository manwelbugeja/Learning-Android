// Copyright 2016 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.android.globalactionbarservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.ArrayDeque;
import java.util.Deque;


public class GlobalActionBarService extends AccessibilityService {
    void debug(String msg) {
        Toast toast=Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }

    FrameLayout mLayout;
    void setLayoutView() {
        // Create an overlay and display the action bar
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayout = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.RIGHT;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.action_bar, mLayout);
        wm.addView(mLayout, lp);
    }

    @Override
    protected void onServiceConnected() {
        setLayoutView();

        configurePowerButton();
        configureVolumeUpButton();
        configureScrollButton();
        configureSwipeButton();
        configureCloseButton();
        configureHideButton();
        configureTapButton();
        configureSetTapButton();
        configureSetTapCountButton();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    private void configurePowerButton() {
        Button powerButton = (Button) mLayout.findViewById(R.id.power);
        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performGlobalAction(GLOBAL_ACTION_POWER_DIALOG);
            }
        });
    }

    private void configureVolumeUpButton() {
        Button volumeUpButton = (Button) mLayout.findViewById(R.id.volume_up);
        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            }
        });
    }

    private AccessibilityNodeInfo findScrollableNode(AccessibilityNodeInfo root) {
        Deque<AccessibilityNodeInfo> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            AccessibilityNodeInfo node = deque.removeFirst();
            if (node.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)) {
                return node;
            }
            for (int i = 0; i < node.getChildCount(); i++) {
                deque.addLast(node.getChild(i));
            }
        }
        return null;
    }

    private void configureScrollButton() {
        Button scrollButton = (Button) mLayout.findViewById(R.id.scroll);
        scrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessibilityNodeInfo scrollable = findScrollableNode(getRootInActiveWindow());
                if (scrollable != null) {
                    scrollable.performAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD.getId());
                }
            }
        });
    }

    private void configureSwipeButton() {
        Button swipeButton = (Button) mLayout.findViewById(R.id.swipe);
        swipeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Path swipePath = new Path();
                swipePath.moveTo(1000, 1000);
                GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
                gestureBuilder.addStroke(new GestureDescription.StrokeDescription(swipePath, 0, 500));
                dispatchGesture(gestureBuilder.build(), null, null);
            }
        });
    }

    private void configureCloseButton() {
        Button closeButton = (Button) mLayout.findViewById(R.id.close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                disableSelf();
            }
        });
    }

    private void showLayout(boolean show) {
        if (show) {
            mLayout.setVisibility(View.VISIBLE);
        }

        else {
            mLayout.setVisibility(View.GONE);
        }
    }

    private void configureHideButton() {
        Button hideButton = (Button) mLayout.findViewById(R.id.hide);
        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLayout(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showLayout(true);
                    }
                }, 5000);
            }
        });
    }

    // (x, y) in screen coordinates
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static GestureDescription createClick(float x, float y) {
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

    FrameLayout fullscreenOverlay;
    private void displayFullscreenButton() {
        // Create an overlay and display the action bar
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        fullscreenOverlay = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        //lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //lp.gravity = Gravity.RIGHT;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.full_screen_layout, fullscreenOverlay);
        wm.addView(fullscreenOverlay, lp);
    }

    private void removeFullscreenButton() {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.removeView(fullscreenOverlay);
    }

    public static class Coordinates {
        int x = 0;
        int y = 0;
    }

    @SuppressLint("ClickableViewAccessibility")
    Coordinates getTapCoordinates() {
        displayFullscreenButton();

        Coordinates coordinates = new Coordinates();

        Button tapLocationButton = (Button) fullscreenOverlay.findViewById(R.id.tap_location);

        // BUG run_twice: runs twice
        tapLocationButton.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int oldX = coordinates.x;
                int oldY = coordinates.y;

                coordinates.x = (int)event.getX();
                coordinates.y = (int)event.getY();

                // temp code to fix bug run_twice
                if (coordinates.x == 0 && coordinates.y ==  0) {
                    coordinates.x = oldX;
                    coordinates.y = oldY;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                }


                removeFullscreenButton();
                debug("GOT: " + coordinates.x + ", " +  coordinates.y);
                return false;
            }
        });

        return coordinates;
    }

    FrameLayout tapCountPromptLayout;
    void displayTapCountPromptLayout() {
        // Create an overlay and display the action bar
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        tapCountPromptLayout = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        //lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //lp.gravity = Gravity.RIGHT;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.prompt_tap_count, tapCountPromptLayout);
        wm.addView(tapCountPromptLayout, lp);
    }

    private void removeTapCountPromptLayout() {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.removeView(tapCountPromptLayout);
    }

    int tapCount = 1;
    private int getTapCount() {
        displayTapCountPromptLayout();

        Button confirmTapCount = (Button) tapCountPromptLayout.findViewById(R.id.confirm_button);
        confirmTapCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tapCountInput = (EditText) tapCountPromptLayout.findViewById(R.id.tap_count_input);
                tapCount = Integer.parseInt(tapCountInput.getText().toString());

                removeTapCountPromptLayout();
            }
        });

        return tapCount;
    }

    Coordinates coordinates;
    private void configureTapButton() {
        Button tapButton = (Button) mLayout.findViewById(R.id.tap);
        tapButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
                debug("DISPATCHING: "+ coordinates.x +", " + coordinates.y);

                for (int i = 0; i < tapCount; ++i) {
                    boolean result = dispatchGesture(createClick(coordinates.x, coordinates.y), null, null);
                }
            }
        });
    }

    private void configureSetTapButton() {
        Button setTapButton = (Button) mLayout.findViewById(R.id.set_tap);
        setTapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordinates = getTapCoordinates();
            }
        });
    }

    private void configureSetTapCountButton() {
        Button setTapCountButton = (Button) mLayout.findViewById(R.id.set_tap_count);
        setTapCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapCount = getTapCount();
            }
        });
    }


}
