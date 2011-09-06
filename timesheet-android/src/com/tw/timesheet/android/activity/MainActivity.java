package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.MainActivityCallback;
import com.tw.timesheet.android.activity.presenter.MainActivityPresenter;

public class MainActivity extends Activity implements MainActivityCallback {

    MainActivityPresenter presenter = new MainActivityPresenter(this);
    private TextView title;
    private Button addTimesheetButton;
    private Button viewTimesheetButton;
    private Button settingButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bindData();
        initUI();
        setListeners();
    }

    private void bindData() {
        //if this is only a show text, just put it into initUI,
        //but if this is changed by behaviors, then bind it with presenter
        presenter.setUsername(getIntent().getStringExtra("username"));
    }

    private void initUI() {
        addTimesheetButton = (Button) findViewById(R.id.bt_add_time_sheet);
        viewTimesheetButton = (Button) findViewById(R.id.bt_edit_time_sheet);
        settingButton = (Button) findViewById(R.id.bt_setting);
        title = (TextView) findViewById(R.id.text_login);
        presenter.initUI();
    }

    private void setListeners() {
        addTimesheetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.clickAddTimesheetButton();
            }
        });
        viewTimesheetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.clickViewTimesheetButton();
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.clickSettingButton();
            }
        });
    }

    @Override
    public void setTitleText(String prompt) {
        title.setText(prompt);
    }

    @Override
    public void startNextActivity(Class activityClass) {
        Intent intent = new Intent();
        intent.setClass(this, activityClass);
        startActivity(intent);
    }

    @Override
    public void closeActivity() {
        this.finish();
    }
}
