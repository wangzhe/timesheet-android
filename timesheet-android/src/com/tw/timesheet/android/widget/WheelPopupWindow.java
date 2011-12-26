package com.tw.timesheet.android.widget;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.domain.ScreenSizeUtil;
import com.tw.timesheet.android.widget.wheel.WheelView;
import com.tw.timesheet.android.widget.wheel.adapters.ArrayWheelAdapter;
import com.tw.timesheet.android.widget.wheel.adapters.NumericWheelAdapter;

public class WheelPopupWindow extends PopupWindow {

    private Context context;
    private static final int DP_VALUE = 200;

    public WheelPopupWindow(Context context) {
        super(context);
        this.context = context;
        initUI();
    }

    private void initUI() {
        removeDefaultWindowBackground();
        setWidth(FrameLayout.LayoutParams.FILL_PARENT);
        setHeight(ScreenSizeUtil.getPxFromDp(context.getResources().getDisplayMetrics().density, DP_VALUE));

        initWheels();
    }

    private void initWheels() {
        initWeekDays(4);
        initHours(6);
        initMins(0);
        initAMPM(1);
    }

    private void removeDefaultWindowBackground() {
        setBackgroundDrawable(null);
    }

    private void initWeekDays(int weekDay) {
        final WheelView weekDayWheel = (WheelView) findViewById(R.id.weekday);
        ArrayWheelAdapter<String> weekDayAdapter =
                new ArrayWheelAdapter<String>(context, new String[]
                        {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        weekDayAdapter.setItemResource(R.layout.wheel_text_item_blue);
        weekDayAdapter.setItemTextResource(R.id.text);

        weekDayWheel.setViewAdapter(weekDayAdapter);
        weekDayWheel.setCurrentItem(weekDay);
    }

    private void initHours(int hour) {
        final WheelView hoursWheel = (WheelView) findViewById(R.id.hour);
        NumericWheelAdapter hourAdapter = new NumericWheelAdapter(context, 1, 12, "%02d");
        hourAdapter.setItemResource(R.layout.wheel_text_item);
        hourAdapter.setItemTextResource(R.id.text);

        hoursWheel.setViewAdapter(hourAdapter);
        hoursWheel.setCurrentItem(hour);
    }

    private void initMins(int min) {
        final WheelView minsWheel = (WheelView) findViewById(R.id.mins);
        NumericWheelAdapter minAdapter = new NumericWheelAdapter(context, 0, 59, "%02d");
        minAdapter.setItemResource(R.layout.wheel_text_item);
        minAdapter.setItemTextResource(R.id.text);

        minsWheel.setViewAdapter(minAdapter);
        minsWheel.setCurrentItem(min);
        minsWheel.setCyclic(true);
    }


    private void initAMPM(int ampm) {
        final WheelView ampmWheel = (WheelView) findViewById(R.id.ampm);
        ArrayWheelAdapter<String> ampmAdapter =
                new ArrayWheelAdapter<String>(context, new String[]{"AM", "PM"});
        ampmAdapter.setItemResource(R.layout.wheel_text_item);
        ampmAdapter.setItemTextResource(R.id.text);

        ampmWheel.setViewAdapter(ampmAdapter);
        ampmWheel.setCurrentItem(ampm);
    }

}
