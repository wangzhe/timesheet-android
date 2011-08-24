package com.tw.timesheet.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.activity.callback.MainActivityCallback;
import com.tw.timesheet.android.activity.presenter.MainActivityPresenter;

public class MainActivity extends Activity implements MainActivityCallback {

    MainActivityPresenter presenter = new MainActivityPresenter(this);
    private TextView text;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
        setListeners();
    }

    private void initUI() {
        loginButton = (Button) findViewById(R.id.bt_login);
        text = (TextView) findViewById(R.id.text_login);
    }

    private void setListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.clickLoginButton();
            }
        });
    }

    public void setLoginResultText(String prompt) {
        text.setText(prompt);
    }
}

