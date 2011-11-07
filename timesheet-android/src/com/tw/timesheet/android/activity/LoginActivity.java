package com.tw.timesheet.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.LoginActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.presenter.LoginActivityPresenter;
import com.tw.timesheet.android.system.DeviceSystem;

public class LoginActivity extends TimeSheetActivity implements LoginActivityView, DeviceSystem {

    LoginActivityPresenter presenter = new LoginActivityPresenter(this, this);
    private Button loginButton;
    private Button resetButton;
    private EditText passwordEditText;
    private EditText usernameEditText;
    private TextView statusText;
    private StatusData statusData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        bindData();
        initUI();
        setListeners(new UserProfile(getUsername(), getPassword()), presenter);
    }

    protected void bindData() {
        statusData = (StatusData) getIntent().getSerializableExtra("statusData");
    }

    protected void initUI() {
        statusText = (TextView) findViewById(R.id.login_screen_status_text);
        usernameEditText = (EditText) findViewById(R.id.login_screen_username_edit);
        passwordEditText = (EditText) findViewById(R.id.login_screen_password_edit);
        loginButton = (Button) findViewById(R.id.login_screen_login_button);
        resetButton = (Button) findViewById(R.id.login_screen_reset_button);
        usernameEditText.setText(statusData.getUsername());
    }

    @Override
    public void setLoginButtonOnClickListener(View.OnClickListener listener) {
        loginButton.setOnClickListener(listener);
    }

    @Override
    public void setResetButtonOnClickListener(View.OnClickListener listener) {
        resetButton.setOnClickListener(listener);
    }

    @Override
    public void setUsernameEditText(String prompt) {
        usernameEditText.setText(prompt);
    }

    @Override
    public void setPasswordEditText(String prompt) {
        passwordEditText.setText(prompt);
    }

    @Override
    public void setStatusText(String prompt) {
        statusText.setVisibility(View.VISIBLE);
        statusText.setText(prompt);
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    public void setListeners(final UserProfile userProfile, final LoginActivityPresenter loginActivityPresenter) {
        setLoginButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityPresenter.login(userProfile);
            }
        });
        setResetButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginActivityPresenter.reset();
            }
        });
    }
}

