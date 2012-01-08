package com.tw.timesheet.android.componment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.presenter.SettingActivityPresenter;
import com.tw.timesheet.android.widget.NotificationEntryView;

public class SettingNotifyArea extends RelativeLayout {

    private Context context;
    private LinearLayout notifyListView;
    private RelativeLayout addNotificationView;
    private SettingActivityPresenter presenter;
    private NotificationEntryView currentEditView;

    public SettingNotifyArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initUI();
    }

    private void initUI() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View notifyArea = inflater.inflate(R.layout.setting_notify_area, this, false);
        this.addView(notifyArea);
        
        notifyListView = (LinearLayout) notifyArea.findViewById(R.id.setting_notify_area_linear_list_view);
        addNotificationView = (RelativeLayout) findViewById(R.id.setting_notify_area_add_notification);
    }

    public void init(SettingActivityPresenter presenter) {
        this.presenter = presenter;
        setListeners(presenter);
    }

    private void setListeners(final SettingActivityPresenter presenter) {
        addNotificationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationEntryView entryView = createNotifyEntryView(notifyListView);
                notifyListView.addView(entryView);
                presenter.addNotificationClicked();
            }
        });
    }

    private NotificationEntryView createNotifyEntryView(final LinearLayout notifyListView) {
        final NotificationEntryView entryView = new NotificationEntryView(context);
        entryView.setFocusable(true);
        entryView.setRemoveButtonOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyListView.removeView(entryView);
            }
        });
        return entryView;
    }
}
