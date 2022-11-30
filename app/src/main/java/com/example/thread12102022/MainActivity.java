package com.example.thread12102022;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int a, b, c;
    MyFlag myFlag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = b = c = 0;
        myFlag = new MyFlag(0);

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 0; i < 50;) {
                        if (myFlag.count == 0) {
                            a = i;
                            Log.d("BBB", "A: " + a);
                            myFlag.count = 1;
                            i++;
                            myFlag.notifyAll();
                        } else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 0; i < 50;) {
                        if (myFlag.count == 1) {
                            b = i;
                            Log.d("BBB", "B: " + b);
                            myFlag.count = 2;
                            i++;
                            myFlag.notifyAll();
                        } else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 0; i < 50;) {
                       if (myFlag.count == 2) {
                           c = a + b;
                           Log.d("BBB", "C: " + c);
                           myFlag.count = 0;
                           i++;
                           myFlag.notifyAll();
                       } else {
                           try {
                               myFlag.wait();
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                }
            }
        });

        threadC.start();
        threadA.start();
        threadB.start();
    }

}
