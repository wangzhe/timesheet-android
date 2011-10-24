package com.tw.timesheet.android.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {
    private LoginActivity loginActivity;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private Button resetButton;
    private TextView usernameLabel;
    private TextView passwordLabel;

    @Before
    public void setup() {
        loginActivity = new LoginActivity();
        Intent newIntent = new Intent();
        newIntent.putExtra("username", "User Name");
        loginActivity.setIntent(newIntent);
        loginActivity.onCreate(null);
        usernameLabel = (TextView) loginActivity.findViewById(R.id.user_name_edit);
        usernameEdit = (EditText) loginActivity.findViewById(R.id.user_name_edit);
        passwordLabel = (TextView) loginActivity.findViewById(R.id.password_label);
        passwordEdit = (EditText) loginActivity.findViewById(R.id.password_edit);
        loginButton = (Button) loginActivity.findViewById(R.id.login_button);
        resetButton = (Button) loginActivity.findViewById(R.id.resetButton);
    }

    @Test
    public void should_display_all_widget_in_login_activity() {
        assertNotNull(loginActivity);
        assertThat(getTextFromWidget(R.id.user_name_edit), equalTo("User Name"));
        assertThat(getTextFromWidget(R.id.password_label), equalTo("Password"));
        assertThat(getTextFromWidget(R.id.login_button), equalTo("Login"));
        assertThat(getTextFromWidget(R.id.resetButton), equalTo("Reset"));
    }

    private String getTextFromWidget(int widgetId) {
        return ((TextView) loginActivity.findViewById(widgetId)).getText().toString();
    }
}
