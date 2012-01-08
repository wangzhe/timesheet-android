package com.tw.timesheet.android.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.tw.timesheet.android.R;

public class NotificationEntryView extends LinearLayout {

    private Context context;
    private View removeButton;

    public NotificationEntryView(Context context) {
        super(context);
        this.context = context;
        initUI();
    }

    private void initUI() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.notification_entry_view, this, false);
        this.addView(content);
        removeButton = content.findViewById(R.id.notification_entry_remove_icon);
    }

    public void setRemoveButtonOnClickListener(OnClickListener listener) {
        removeButton.setOnClickListener(listener);
    }
}
