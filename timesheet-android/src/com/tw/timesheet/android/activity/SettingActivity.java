package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.SettingActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.presenter.SettingActivityPresenter;
import com.tw.timesheet.android.widget.SettingItemView;
import com.tw.timesheet.android.widget.WheelPopupWindow;
import com.tw.timesheet.android.widget.wheel.WheelView;
import com.tw.timesheet.android.widget.wheel.adapters.ArrayWheelAdapter;
import com.tw.timesheet.android.widget.wheel.adapters.NumericWheelAdapter;

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
    private LinearLayout NotificationListView;

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
        usernameView = (SettingItemView) findViewById(R.id.setting_screen_username);
        departmentView = (SettingItemView) findViewById(R.id.setting_screen_department);
        countryView = (SettingItemView) findViewById(R.id.setting_screen_country);
        isOnBgServCheckBox = (CheckBox) findViewById(R.id.setting_screen_active_background_service);
        NotificationListView = (LinearLayout) findViewById(R.id.setting_screen_notification_linear_list_view);

        initWeekDays(4);
        initHours(6);
        initMins(0);
        initAMPM(1);
    }

    private void bindData() {
        presenter = new SettingActivityPresenter(this, statusData);
        usernameView.setText(statusData.getUsername());
        presenter.init();
    }

    private void setListeners() {
        departmentView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.deptClicked();
            }
        });
        addNotificationView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.addNotificationClicked();
            }
        });
    }

    private void initWeekDays(int weekDay) {
        final WheelView weekDayWheel = (WheelView) findViewById(R.id.weekday);
        ArrayWheelAdapter<String> weekDayAdapter =
                new ArrayWheelAdapter<String>(this, new String[]
                        {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        weekDayAdapter.setItemResource(R.layout.wheel_text_item_blue);
        weekDayAdapter.setItemTextResource(R.id.text);

        weekDayWheel.setViewAdapter(weekDayAdapter);
        weekDayWheel.setCurrentItem(weekDay);
    }

    private void initHours(int hour) {
        final WheelView hoursWheel = (WheelView) findViewById(R.id.hour);
        NumericWheelAdapter hourAdapter = new NumericWheelAdapter(this, 1, 12, "%02d");
        hourAdapter.setItemResource(R.layout.wheel_text_item);
        hourAdapter.setItemTextResource(R.id.text);

        hoursWheel.setViewAdapter(hourAdapter);
        hoursWheel.setCurrentItem(hour);
    }

    private void initMins(int min) {
        final WheelView minsWheel = (WheelView) findViewById(R.id.mins);
        NumericWheelAdapter minAdapter = new NumericWheelAdapter(this, 0, 59, "%02d");
        minAdapter.setItemResource(R.layout.wheel_text_item);
        minAdapter.setItemTextResource(R.id.text);

        minsWheel.setViewAdapter(minAdapter);
        minsWheel.setCurrentItem(min);
        minsWheel.setCyclic(true);
    }


    private void initAMPM(int ampm) {
        final WheelView ampmWheel = (WheelView) findViewById(R.id.ampm);
        ArrayWheelAdapter<String> ampmAdapter =
                new ArrayWheelAdapter<String>(this, new String[]{"AM", "PM"});
        ampmAdapter.setItemResource(R.layout.wheel_text_item);
        ampmAdapter.setItemTextResource(R.id.text);

        ampmWheel.setViewAdapter(ampmAdapter);
        ampmWheel.setCurrentItem(ampm);
    }

    @Override
    public void setDeptToggle(int resId) {
        departmentView.setIcon(resId);
    }

    @Override
    public void showWheelPopupWindow() {
        popupWindow = new WheelPopupWindow(this);
        popupWindow.showNoResult();
    }
}
