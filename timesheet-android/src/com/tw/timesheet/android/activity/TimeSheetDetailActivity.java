package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ListView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.system.DeviceSystem;
import com.tw.timesheet.android.widget.wheel.OnWheelChangedListener;
import com.tw.timesheet.android.widget.wheel.OnWheelScrollListener;
import com.tw.timesheet.android.widget.wheel.WheelView;
import com.tw.timesheet.android.widget.wheel.adapters.NumericWheelAdapter;

public class TimeSheetDetailActivity extends TimeSheetActivity implements DeviceSystem {
    private ListView timeSheetListView;
    private Button addNewButton;

//    TimeSheetContentPresenter presenter = new TimeSheetContentPresenter(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_sheet_detail_screen);
        bindData();
        initUI();
        setListeners();
    }

    private void setListeners() {

    }

    private void bindData() {
    }

    private void initUI() {
//        timeSheetListView = (ListView) findViewById(R.id.time_sheet_detail_screen_list_view);
        addNewButton = (Button) findViewById(R.id.time_sheet_detail_screen_new);

        initWheel(R.id.time_sheet_detail_screen_mon_hour, 8);
        initWheel(R.id.time_sheet_detail_screen_tue_hour, 8);
        initWheel(R.id.time_sheet_detail_screen_wed_hour, 8);
        initWheel(R.id.time_sheet_detail_screen_thu_hour, 8);
        initWheel(R.id.time_sheet_detail_screen_fri_hour, 8);
    }


    // Wheel scrolled flag
    private boolean wheelScrolled = false;

    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            updateDayHours();
        }
    };

    // Wheel changed listener
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                updateDayHours();
            }
        }
    };

    private void updateDayHours() {
        //update text
    }

    /**
     * Initializes wheel
     * @param id the wheel widget Id
     * @param hours
     */
    private void initWheel(int id, int hours) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new NumericWheelAdapter(this, 0, 24));
        wheel.setCurrentItem(hours);

        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
        wheel.setCyclic(true);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
    }

    /**
     * Returns wheel by Id
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
    	return (WheelView) findViewById(id);
    }
}
