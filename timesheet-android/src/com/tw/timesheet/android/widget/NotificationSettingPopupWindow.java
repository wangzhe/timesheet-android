package com.tw.timesheet.android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.domain.ScreenSizeUtil;
import com.tw.timesheet.android.widget.wheel.WheelView;
import com.tw.timesheet.android.widget.wheel.adapters.ArrayWheelAdapter;
import com.tw.timesheet.android.widget.wheel.adapters.NumericWheelAdapter;

public class NotificationSettingPopupWindow extends PopupWindow {

    private Context context;
    private static final int DP_VALUE = 200;
    private View popupView;
    private View anchorImage;

    public NotificationSettingPopupWindow(Context context, View anchorImage) {
        super(context);
        this.context = context;
        this.anchorImage = anchorImage;
        initUI();
    }

    private void initUI() {
        removeDefaultWindowBackground();
        setWidth(FrameLayout.LayoutParams.FILL_PARENT);
        setHeight(ScreenSizeUtil.getPxFromDp(context.getResources().getDisplayMetrics().density, DP_VALUE));
        popupView = initMessageView();
        setContentView(popupView);
        setAnimationStyle(R.style.PopUpWindowAnimation);
    }

    private View initMessageView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpMessageView = inflater.inflate(R.layout.wheel_popup_window, null);
        LinearLayout content = (LinearLayout) popUpMessageView.findViewById(R.id.pop_up_content);
        content.setBackgroundResource(R.color.black);
        initWheels(content);

        return popUpMessageView;
    }

    private void initWheels(LinearLayout wheelContent) {
        wheelContent.addView(initWeekDays(4));
        wheelContent.addView(initHours(6));
        wheelContent.addView(initMins(3));
        wheelContent.addView(initAMPM(1));
    }

    private void removeDefaultWindowBackground() {
        setBackgroundDrawable(null);
    }

    private WheelView initWeekDays(int weekDay) {
        final WheelView weekDayWheel = new WheelView(context);
        ArrayWheelAdapter<String> weekDayAdapter =
                new ArrayWheelAdapter<String>(context, new String[]
                        {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        weekDayAdapter.setItemResource(R.layout.wheel_text_item_blue);
        weekDayAdapter.setItemTextResource(R.id.text);

        weekDayWheel.setViewAdapter(weekDayAdapter);
        weekDayWheel.setCurrentItem(weekDay);
        return weekDayWheel;
    }

    private WheelView initHours(int hour) {
        final WheelView hoursWheel = new WheelView(context);
        NumericWheelAdapter hourAdapter = new NumericWheelAdapter(context, 1, 12, "%02d");
        hourAdapter.setItemResource(R.layout.wheel_text_item);
        hourAdapter.setItemTextResource(R.id.text);

        hoursWheel.setViewAdapter(hourAdapter);
        hoursWheel.setCurrentItem(hour);
        return hoursWheel;
    }

    private WheelView initMins(int min) {
        final WheelView minsWheel = new WheelView(context);
        ArrayWheelAdapter<String> minAdapter =
                new ArrayWheelAdapter<String>(context, new String[]{"15", "30", "45", "00"});
        minAdapter.setItemResource(R.layout.wheel_text_item);
        minAdapter.setItemTextResource(R.id.text);

        minsWheel.setViewAdapter(minAdapter);
        minsWheel.setCurrentItem(min);
        return minsWheel;
    }


    private WheelView initAMPM(int ampm) {
        final WheelView ampmWheel = new WheelView(context);
        ArrayWheelAdapter<String> ampmAdapter =
                new ArrayWheelAdapter<String>(context, new String[]{"AM", "PM"});
        ampmAdapter.setItemResource(R.layout.wheel_text_item);
        ampmAdapter.setItemTextResource(R.id.text);

        ampmWheel.setViewAdapter(ampmAdapter);
        ampmWheel.setCurrentItem(ampm);
        return ampmWheel;
    }

    public void show() {
        showAsDropDown(anchorImage, 0, getOffsetY());
    }

    private int getOffsetY() {
        //should be calculated
        return -71;
    }

}
