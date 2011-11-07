package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.MainActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.presenter.MainActivityPresenter;

public class MainActivity extends TimeSheetActivity implements MainActivityView {

    MainActivityPresenter presenter = new MainActivityPresenter(this);
    private TextView title;
    private Button insertTimeSheetButton;
    private Button viewTimeSheetButton;
    private Button settingButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bindData();
        initUI();
        presenter.setListeners();
    }

    protected void bindData() {
        presenter.setStatusData((StatusData) getIntent().getSerializableExtra("statusData"));
    }

    protected void initUI() {
        insertTimeSheetButton = (Button) findViewById(R.id.bt_add_time_sheet);
        viewTimeSheetButton = (Button) findViewById(R.id.bt_edit_time_sheet);
        settingButton = (Button) findViewById(R.id.bt_setting);
        title = (TextView) findViewById(R.id.main_screen_username_label);
        presenter.initUI();
    }

    @Override
    public void setInsertTimeSheetButtonOnClickListener(View.OnClickListener listener) {
        insertTimeSheetButton.setOnClickListener(listener);
    }

    @Override
    public void setViewTimeSheetButtonOnClickListener(View.OnClickListener listener) {
        viewTimeSheetButton.setOnClickListener(listener);
    }

    @Override
    public void setSettingButtonOnClickListener(View.OnClickListener listener) {
        settingButton.setOnClickListener(listener);
    }

    @Override
    public void setTitleText(String prompt) {
        title.setText(prompt);
    }
}

