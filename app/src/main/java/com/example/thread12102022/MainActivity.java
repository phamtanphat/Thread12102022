package com.example.thread12102022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                loopMessage("A");
            }
        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                loopMessage("B");
            }
        });

        threadA.start();
        threadB.start();
    }

    private synchronized void loopMessage(String message) {
        for (int i = 0; i < 1000; i++) {
            Log.d("BBB", String.format("%s : %d", message, i));
        }
    }
}
