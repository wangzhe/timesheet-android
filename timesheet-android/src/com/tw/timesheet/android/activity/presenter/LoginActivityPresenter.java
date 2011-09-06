package com.tw.timesheet.android.activity.presenter;

import com.tw.timesheet.android.activity.callback.LoginActivityCallback;

public class LoginActivityPresenter {
    private LoginActivityCallback callback;

    public LoginActivityPresenter(LoginActivityCallback callback) {
        this.callback = callback;
    }

    public void clickLoginButton() {
        //I think here the UT should just test whether the text had been set
        callback.setLoginResultText("login successful");
    }
}
