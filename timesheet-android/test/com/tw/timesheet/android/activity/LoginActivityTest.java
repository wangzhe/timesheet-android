package com.tw.timesheet.android.activity;

import android.content.Intent;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tw.timesheet.android.R;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.presenter.LoginActivityPresenter;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {
    private LoginActivity loginActivity;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private Button resetButton;
    private TextView statusText;

    @Before
    public void setup() {
        loginActivity = new LoginActivity();
        Intent newIntent = new Intent();
        newIntent.putExtra("statusData", new StatusData("User Name"));
        loginActivity.setIntent(newIntent);
        loginActivity.onCreate(null);
        usernameEdit = (EditText) loginActivity.findViewById(R.id.login_screen_username_edit);
        passwordEdit = (EditText) loginActivity.findViewById(R.id.login_screen_password_edit);
        statusText = (TextView) loginActivity.findViewById(R.id.login_screen_status_text);
        loginButton = (Button) loginActivity.findViewById(R.id.login_screen_login_button);
        resetButton = (Button) loginActivity.findViewById(R.id.login_screen_reset_button);
    }

    @Test
    public void should_display_proper_widget_in_login_activity() {
        assertNotNull(loginActivity);
        assertThat(getTextFromWidget(R.id.login_screen_username_edit), equalTo("User Name"));
        assertThat(getTextFromWidget(R.id.login_screen_password_label), equalTo("Password"));
        assertThat(getTextFromWidget(R.id.login_screen_login_button), equalTo("Login"));
        assertThat(getTextFromWidget(R.id.login_screen_reset_button), equalTo("Reset"));
        assertThat(getTextFromWidget(R.id.login_screen_status_text), equalTo("Please input account info"));
        assertThat(loginActivity.findViewById(R.id.login_screen_status_text).getVisibility(), is(View.GONE));
    }

    private String getTextFromWidget(int widgetId) {
        return ((TextView) loginActivity.findViewById(widgetId)).getText().toString();
    }

    @Test
    public void should_clean_login_and_password_content_when_reset_button_clicked() {
        LoginActivityPresenter presenter = new LoginActivityPresenter(loginActivity, loginActivity);
        presenter.setListeners(null);
        
        resetButton.performClick();

        assertThat(usernameEdit.getText().toString(), is(""));
        assertThat(passwordEdit.getText().toString(), is(""));
    }

    @Test
    public void should_show_status_text_when_login_button_clicked_but_failed() {
        LoginActivityPresenter presenter = new LoginActivityPresenter(loginActivity, loginActivity);
        UserProfile userProfile = mock(UserProfile.class);
        when(userProfile.login(Matchers.<NetworkInfo>any(), Matchers.<DataServer>any())).thenReturn(null);
        presenter.setListeners(userProfile);

        loginButton.performClick();

        assertThat(statusText.getText().toString(), is("user or password is invalid"));
    }
}
