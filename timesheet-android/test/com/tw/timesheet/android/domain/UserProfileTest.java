package com.tw.timesheet.android.domain;

import android.net.NetworkInfo;
import com.tw.timesheet.android.exception.ConnectionTimeoutException;
import com.tw.timesheet.android.net.DataServer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileTest {

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

        assertThat(userProfile.login(networkInfo, null), is(false));
        assertThat(userProfile.login(null, null), is(false));
    }

    @Test
    public void should_return_false_when_server_connection_timeout_exception() {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        DataServer dataServer = mock(DataServer.class);
        when(dataServer.postHttpRequest(Matchers.<String>any())).thenThrow(new ConnectionTimeoutException());

        assertThat(userProfile.login(networkInfo, dataServer), is(false));
    }

    @Test
    public void should_login_unsuccessful_when_login_failed() {
        UserProfile userProfile = new UserProfile("userA", "pwdA");
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        DataServer dataServer = mock(DataServer.class);
        when(dataServer.postHttpRequest(Matchers.<String>any())).thenReturn(null);

        assertThat(userProfile.login(networkInfo, dataServer), is(false));
    }

    @Test
    public void should_login_unsuccessful_when_return_login_parsing_failed() {
//        UserProfile userProfile = new UserProfile("userA", "pwdA");
//        InputStream resourceAsStream = this.getClass().getResourceAsStream("com/tw/timesheet/android/json/errorloginOKStub.json");
//        ClassLoader classLoader = this.getClass().getClassLoader();
//        URL resource = classLoader.getResource("com/tw/timesheet/android/");
//        InputStream resourceAsStream = classLoader.getResourceAsStream("/");

//        System.out.println("resourceAsStream.toString() = " + resource);
//
//        System.out.println("IOUtil.getStringFromStream(resourceAsStream) = " + IOUtil.getStringFromStream(resourceAsStream));
//
//        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
//        DataServer dataServer = mock(DataServer.class);
//        when(dataServer.postHttpRequest(Matchers.<String>any())).thenReturn(IOUtil.getStringFromStream(resourceAsStream));
//
//        assertThat(userProfile.login(networkInfo, dataServer), is(false));
    }

}
