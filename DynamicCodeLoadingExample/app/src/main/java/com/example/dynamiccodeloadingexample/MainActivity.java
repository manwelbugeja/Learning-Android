package com.example.dynamiccodeloadingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    static String TAG = "DYNAMIC_CODE_LOADING_EXAMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        String path = context.getFilesDir().getAbsolutePath();
        Log.i(TAG, "PATH: " + path);

        String apkPath = path + "/dynamic-code.apk";

        final DexClassLoader classLoader = new DexClassLoader(apkPath, context.getCacheDir().getAbsolutePath(), null, this.getClass().getClassLoader());
        Class<?> cls = null;
        try {
            cls = classLoader.loadClass("com.example.dynamiccode.StringFns");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Method countLettersMethod = null;
        try {
            countLettersMethod = cls.getMethod("countLetters", String.class);
        } catch (NoSuchMethodException e) {
            Log.i(TAG, "No such method!");
            e.printStackTrace();
        }

        // first parameter (Class) is null because the method is static
        int result = 0;
        try {
            result = (int) countLettersMethod.invoke(null, "Hello");
        } catch (IllegalAccessException e) {
            Log.i(TAG, "Illegal Access");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.i(TAG, "Invocation Target Exception");
            e.printStackTrace();
        }

        Log.i(TAG, "Answer: " + result);
    }
}