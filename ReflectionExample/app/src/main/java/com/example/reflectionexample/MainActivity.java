package com.example.reflectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "REFLECTION_EXAMPLE";

    // Reflection Example
    public class MyClass {
        public int addOne(int i) {
            return i + 1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int ans = 0;
        MyClass myClass = new MyClass();
        Class cls = myClass.getClass();
        try {
            Method method = cls.getDeclaredMethod("addOne", int.class);
            ans = (int) method.invoke(myClass, 12);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "ANSWER: " + String.valueOf(ans));
    }
}