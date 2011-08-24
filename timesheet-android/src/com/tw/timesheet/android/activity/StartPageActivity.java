package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tw.timesheet.android.R;

public class StartPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        prepareApp();
    }

    private void startApp() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

    private void prepareApp() {
        new Thread() {

            public void run() {
                try {
                    Thread.sleep(5000);
                    startApp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}