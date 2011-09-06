package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.StartPageActivityCallback;
import com.tw.timesheet.android.activity.presenter.StartPageActivityPresenter;

public class StartPageActivity extends Activity implements StartPageActivityCallback {

    StartPageActivityPresenter presenter = new StartPageActivityPresenter(this);

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

    @Override
    public void startNextActivity(Class activityClass, String username) {
        Intent intent = new Intent();
        intent.setClass(this, activityClass);

        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void prepareApp() {
        presenter.startApp();
    }

    @Override
    public void startNextActivity(Class activityClass) {

    }

    @Override
    public void closeActivity() {
        this.finish();
    }
}