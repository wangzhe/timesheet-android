package com.tw.timesheet.android.activity.callback;

import android.view.View;

public interface LoginActivityView extends ActivityView {
    
    void setUsernameEditText(String prompt);

    void setPasswordEditText(String prompt);

    void setStatusText(String prompt);

    void setLoginButtonOnClickListener(View.OnClickListener listener);

    void setResetButtonOnClickListener(View.OnClickListener listener);

}
