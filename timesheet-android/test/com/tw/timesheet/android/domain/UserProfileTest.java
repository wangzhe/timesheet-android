package com.tw.timesheet.android.domain;

import android.net.NetworkInfo;
import com.tw.timesheet.android.exception.ConnectionTimeoutException;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.util.IOUtil;
import org.apache.http.client.methods.HttpPost;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileTest {

    public static final String DEFAULT_TEST_URI = "uri";
    private NetworkInfo networkInfo;

    @Before
    public void setUp() {
        networkInfo = mock(NetworkInfo.class);
    }

    @Test
    public void should_return_false_when_two_profile_have_different_content() {
        UserProfile userProfileA = new UserProfile("userA", "pwdA");
        UserProfile userProfileAA = new UserProfile("userA", "pwdA");
        UserProfile userProfileADash = new UserProfile("userA", "pwdB");
        UserProfile userProfileB = new UserProfile("userB", "pwdB");
        UserProfile userProfileWithNullUsr = new UserProfile(null, "pwdB");
        UserProfile userProfileWithNullUsrB = new UserProfile(null, "pwdB");
        UserProfile userProfileWithNullPwd = new UserProfile("userB", null);
        UserProfile userProfileWithNullUsrAndNullPwd = new UserProfile(null, null);
        UserProfile userProfileWithNullUsrAndNullPwdB = new UserProfile(null, null);

        assertTrue(userProfileA.equals(userProfileA));
        assertTrue(userProfileA.equals(userProfileAA));
        assertTrue(userProfileWithNullUsrAndNullPwd.equals(userProfileWithNullUsrAndNullPwdB));
        assertFalse(userProfileA.equals(userProfileADash));
        assertFalse(userProfileA.equals(userProfileB));
        assertFalse(userProfileWithNullUsr.equals(userProfileB));
        assertTrue(userProfileWithNullUsr.equals(userProfileWithNullUsrB));
        assertFalse(userProfileWithNullPwd.equals(userProfileA));
        assertFalse(userProfileWithNullPwd.equals(userProfileWithNullUsrAndNullPwd));
        assertFalse(userProfileWithNullUsr.equals(userProfileWithNullUsrAndNullPwd));
        assertFalse(userProfileA.equals(new Object()));
        assertFalse(userProfileA.equals(userProfileWithNullPwd));
        assertFalse(userProfileA.equals(userProfileWithNullUsr));
        assertFalse(userProfileA.equals(null));
    }

    @Test
    public void should_return_true_when_user_profile_no_content() {
        UserProfile userProfile = new UserProfile();
        UserProfile userProfileWithEmptyPwd = new UserProfile("userA", "");
        UserProfile userProfileWithNoName = new UserProfile("", "pwd");

        assertTrue(userProfile.isEmpty());
        assertTrue(userProfileWithEmptyPwd.isEmpty());
        assertTrue(userProfileWithNoName.isEmpty());
    }

    @Test
    public void should_has_default_setting_when_user_profile_has_content() {
        UserProfile userProfile = new UserProfile("userA", "pwdA");

        assertTrue(userProfile.hasDefaultSetting());
    }

    @Test
    public void should_no_default_setting_when_user_profile_has_content() {
        UserProfile userProfile = new UserProfile("", "");

        assertFalse(userProfile.hasDefaultSetting());
    }

    @Test
    public void should_login_unsuccessful_when_login_but_network_connection_failed() {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        when(networkInfo.isConnectedOrConnecting()).thenReturn(false);

        assertThat(userProfile.login(networkInfo, null), IsNull.<UserResource>nullValue());
        assertThat(userProfile.login(null, null), IsNull.<UserResource>nullValue());
    }

    @Test
    public void should_return_false_when_server_connection_timeout_exception() {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        DataServer dataServer = mock(DataServer.class);
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenThrow(new ConnectionTimeoutException());

        assertThat(userProfile.login(networkInfo, dataServer), IsNull.<UserResource>nullValue());
    }

    @Test
    public void should_login_unsuccessful_when_login_failed() {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        DataServer dataServer = mock(DataServer.class);
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn(null);

        assertThat(userProfile.login(networkInfo, dataServer), IsNull.<UserResource>nullValue());
    }

    @Test
    public void should_login_unsuccessful_when_return_login_parsing_failed() throws FileNotFoundException {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/invalidate_login_response.json");
        String response = IOUtil.getStringFromStream(fis);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        DataServer dataServer = mock(DataServer.class);
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn(response);

        assertThat(userProfile.login(networkInfo, dataServer), IsNull.<UserResource>nullValue());
    }

    @Test
    public void should_login_successful_when_return_login_parsing_failed() throws FileNotFoundException {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/login_response_ok.json");
        String response = IOUtil.getStringFromStream(fis);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        DataServer dataServer = mock(DataServer.class);
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn(response);

        UserResource userResource = userProfile.login(networkInfo, dataServer);
        assertThat(userResource, IsNull.<UserResource>notNullValue());
        assertThat(userResource.getURI(""), is("/userAX/"));
    }

    @Test
    public void should_return_no_default_setting_when_username_or_password_is_empty() {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        UserProfile userProfileNoUsername = new UserProfile("", "pwdA");
        UserProfile userProfileNoPassword = new UserProfile("userA", "pwdA");
        userProfileNoPassword.update("userA", "");

        assertThat(userProfile.hasDefaultSetting(), is(true));
        assertThat(userProfileNoUsername.hasDefaultSetting(), is(false));
        assertThat(userProfileNoPassword.hasDefaultSetting(), is(false));
    }
}
