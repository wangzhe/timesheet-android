package com.tw.timesheet.android.activity.callback;

import android.view.View;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.presenter.LoginActivityPresenter;

public interface LoginActivityView extends ActivityView {
    
    void setUsernameEditText(String prompt);

    void setPasswordEditText(String prompt);

    void setStatusText(String prompt);

    void setLoginButtonOnClickListener(View.OnClickListener listener);

    void setResetButtonOnClickListener(View.OnClickListener listener);

    String getPassword();

    String getUsername();

    void setListeners(final UserProfile userProfile, LoginActivityPresenter loginActivityPresenter);
}
