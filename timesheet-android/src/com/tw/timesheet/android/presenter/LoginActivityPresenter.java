package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.callback.LoginActivityView;

public class LoginActivityPresenter {
    private LoginActivityView view;

    public LoginActivityPresenter(LoginActivityView view) {
        this.view = view;
    }

    public void loginButtonClicked() {
        //I think here the UT should just test whether the text had been set
        view.setLoginResultText("login successful");
    }
}
