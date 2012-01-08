package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.SettingActivityView;
import com.tw.timesheet.android.componment.SettingNotifyArea;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.presenter.SettingActivityPresenter;
import com.tw.timesheet.android.widget.NotificationSettingPopupWindow;
import com.tw.timesheet.android.widget.SettingItemView;
import com.tw.timesheet.android.widget.TitleBar;

public class SettingActivity extends TimeSheetActivity implements SettingActivityView {

    private Button saveButton;
    private Button cancelButton;
    private StatusData statusData;

    private SettingItemView departmentView;
    private SettingItemView countryView;
    private CheckBox isOnBgServCheckBox;
    private SettingActivityPresenter presenter;
    private SettingItemView usernameView;
    private RelativeLayout addNotificationView;
    private SettingNotifyArea notifyArea;
    private NotificationSettingPopupWindow popupWindow;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_screen);
        loadDate();
        initUI();
        bindData();
        setListeners();
    }

    private void loadDate() {
        statusData = (StatusData) getIntent().getSerializableExtra("statusData");
    }

    private void initUI() {
        titleBar = (TitleBar) findViewById(R.id.setting_screen_title_bar);
        usernameView = (SettingItemView) findViewById(R.id.setting_screen_username);
        departmentView = (SettingItemView) findViewById(R.id.setting_screen_department);
        countryView = (SettingItemView) findViewById(R.id.setting_screen_country);
        isOnBgServCheckBox = (CheckBox) findViewById(R.id.setting_screen_active_background_service);
        notifyArea = (SettingNotifyArea) findViewById(R.id.setting_screen_notify_area);
    }

    private void bindData() {
        presenter = new SettingActivityPresenter(this, statusData);
        usernameView.setText(statusData.getUsername());
        presenter.init();
        notifyArea.init(presenter);
    }

    private void setListeners() {
        departmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deptClicked();
            }
        });
    }

    @Override
    public void setDeptToggle(int resId) {
        departmentView.setIcon(resId);
    }

    @Override
    public void showWheelPopupWindow() {
        findViewById(R.id.setting_screen_wheel_fill_up_area).setVisibility(View.VISIBLE);
        if (popupWindow == null) {
            View anchorImage = findViewById(R.id.popup_window_anchor_image);
            popupWindow = new NotificationSettingPopupWindow(this, anchorImage);
            popupWindow.show();
        }
    }
}
