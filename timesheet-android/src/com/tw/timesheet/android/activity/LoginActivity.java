package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.LoginActivityView;
import com.tw.timesheet.android.presenter.LoginActivityPresenter;

public class LoginActivity extends Activity implements LoginActivityView
{

    LoginActivityPresenter presenter = new LoginActivityPresenter(this);
    private TextView text;
    private Button loginButton;
    private String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        bindData();
        initUI();
        setListeners();
    }

    private void bindData() {
        System.out.println(getIntent());
        username = getIntent().getStringExtra("username");
    }

    private void initUI() {
        loginButton = (Button) findViewById(R.id.login_button);
        text = (EditText) findViewById(R.id.user_name_edit);
        text.setText(username);
    }

    private void setListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });
    }

    public void setLoginResultText(String prompt) {
        text.setText(prompt);
    }
}

