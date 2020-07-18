package com.umkc.icp4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StoreActivity extends AppCompatActivity {
    EditText input;
    TextView displayText;
    private File pathSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        // Get the edit text components
        input = findViewById(R.id.txtData);
        displayText = findViewById(R.id.lblData);
        // Create files with the timestamp when the application is opened
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String filename = "android" + timeStamp + ".txt";

        // Get the external Path directory for saving the files
        pathSave = new File(getFilesDir(), filename);
    }

    public void save(View view) {
        // Get the input entered by user
        String text = input.getText().toString();
        FileOutputStream fos = null;
        try {
            // Open File Stream to write the input to file in append mode
            fos = new FileOutputStream(pathSave);
            fos.write(text.getBytes());
            input.getText().clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    // Should always make sure to close the file even in case of exceptions
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View view) {
        FileInputStream fis = null;
        try {
            // Opening file input stream to get the data from the file
            fis = new FileInputStream(pathSave);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            // Reading the file contents line by line
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            // Displaying the text in the view UI
            displayText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    // Should always close the file Input stream even in case of exceptions
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
