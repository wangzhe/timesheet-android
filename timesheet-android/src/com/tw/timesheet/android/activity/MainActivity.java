package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.MainActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.presenter.MainActivityPresenter;
import com.tw.timesheet.android.widget.TitleBar;

public class MainActivity extends TimeSheetActivity implements MainActivityView {

    MainActivityPresenter presenter = new MainActivityPresenter(this);
    private TitleBar titleBar;
    private Button insertTimeSheetButton;
    private Button viewTimeSheetButton;
    private Button settingButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bindData();
        initUI();
        setListeners(presenter);
    }

    protected void bindData() {
        presenter.setStatusData((StatusData) getIntent().getSerializableExtra("statusData"));
    }

    protected void initUI() {
        insertTimeSheetButton = (Button) findViewById(R.id.main_screen_add_time_sheet_button);
        viewTimeSheetButton = (Button) findViewById(R.id.main_screen_edit_time_sheet_button);
        settingButton = (Button) findViewById(R.id.bt_setting);
        titleBar = (TitleBar) findViewById(R.id.main_screen_title_bar);
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
    public void setListeners(final MainActivityPresenter mainActivityPresenter) {
        setInsertTimeSheetButtonOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mainActivityPresenter.addTimeSheetButtonClicked();
            }
        });

        setViewTimeSheetButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityPresenter.viewTimeSheetButtonClicked();
            }
        });

        setSettingButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityPresenter.settingButtonClicked();
            }
        });
    }

    @Override
    public void setTitleText(String prompt) {
        titleBar.setLabelText(prompt);
    }
}

