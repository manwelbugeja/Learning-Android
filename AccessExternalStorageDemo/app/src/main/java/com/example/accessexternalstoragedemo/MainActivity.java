package com.example.accessexternalstoragedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static String TAG="ACCESS_EXTERNAL_STORAGE";
    List<File> pdf;  //declare globally or as per your need

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(pdf==null)  //check for null
            pdf = new ArrayList<>(); //initialise it on your onCreate method
    }

    public void uploadImage(View view) {
        Log.i(TAG, "Button clicked!");

        // Request required permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        // If no scoped storage
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {

            Log.i(TAG, "Android version < 11");

            // Get external directory path
            String external_dir = Environment.getExternalStorageDirectory()
                    .getAbsolutePath().toString();
            Log.i(TAG, "External Dir: " + external_dir);

            File file = new File(external_dir);
            searchFolderRecursive1(file);

        } else {

            Log.i(TAG, "Android version >= 11");
            pdf = getPdfList();
            Log.i(TAG, pdf.toString());

        }



    }

    private static void searchFolderRecursive1(File folder) {
        Log.i(TAG, "Searching in " + folder.toString());
        if (folder != null) {
            if (folder.listFiles() != null) {
                for (File file : folder.listFiles()) {
                    if (file.isFile()) {
                        Log.v(TAG, "Found file: " + file.getName());
                        if(file.getName().contains(".pdf")){
                            Log.v(TAG, "FOUND PDF! path__="+file.getName());
                        }
                    } else {
                        searchFolderRecursive1(file);
                    }
                }
            }
        }
    }


    public List<File> getPdfList() {
        Log.i(TAG, "Getting pdf list");
        Uri collection;

        final String[] projection = new String[]{
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.MIME_TYPE
        };

        final String sortOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        final String selection = MediaStore.Files.FileColumns.MIME_TYPE + " = ?";

        final String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
        final String[] selectionArgs = new String[]{mimeType};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Files.getContentUri("external");
        }


        try (Cursor cursor = getApplicationContext().getApplicationContext().getContentResolver().query(collection, projection, selection, selectionArgs, sortOrder)) {
            assert cursor != null;

            if (cursor.moveToFirst()) {
                int columnData = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
                int columnName = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME);
                do {
                    String path = cursor.getString(columnData);
                    File file = new File(path);
                    if (file.exists()) {
                        //you can get your pdf files
                        pdf.add(new File((cursor.getString(columnData))));
                        Log.d(TAG, "getPdf: " + cursor.getString(columnData));

                    }
                } while (cursor.moveToNext());
            }
        }

        return pdf;
    }
}