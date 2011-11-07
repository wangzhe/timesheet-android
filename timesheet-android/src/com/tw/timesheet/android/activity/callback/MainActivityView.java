package com.tw.timesheet.android.activity.callback;

import android.view.View;

public interface MainActivityView extends ActivityView {
    void setTitleText(String prompt);

    void setInsertTimeSheetButtonOnClickListener(View.OnClickListener listener);

    void setViewTimeSheetButtonOnClickListener(View.OnClickListener listener);

    void setSettingButtonOnClickListener(View.OnClickListener listener);
}
