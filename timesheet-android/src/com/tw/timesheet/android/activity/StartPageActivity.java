package com.tw.timesheet.android.activity;

import android.os.Bundle;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.StartPageActivityView;
import com.tw.timesheet.android.presenter.StartPageActivityPresenter;

public class StartPageActivity extends TimeSheetActivity implements StartPageActivityView {

    StartPageActivityPresenter presenter = new StartPageActivityPresenter(this, this);

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

    private void prepareApp() {
        presenter.startApp();
    }
}