package com.tw.timesheet.android.presenter;

import android.net.NetworkInfo;
import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.callback.LoginActivityView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.domain.UserResource;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.storage.StorageRepository;
import com.tw.timesheet.android.system.DeviceSystem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class LoginActivityPresenterTest {

    private LoginActivityPresenter loginActivityPresenter;
    private LoginActivityView viewer;
    private DeviceSystem device;

    @Before
    public void setUp() {
        viewer = mock(LoginActivityView.class);
        device = mock(DeviceSystem.class);
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(device.getActiveNetworkInfo()).thenReturn(networkInfo);
        loginActivityPresenter = new LoginActivityPresenter(viewer, device);
    }

    @Test
    public void should_reset_username_and_password() {
        loginActivityPresenter.reset();

        verify(viewer).setUsernameEditText(eq(""));
        verify(viewer).setPasswordEditText(eq(""));
    }

    @Test
    public void should_stay_in_the_activity_and_show_unsuccessful_text_when_login_failed() {
        UserProfile userProfile = mock(UserProfile.class);
        when(userProfile.login(Matchers.<NetworkInfo>any(), Matchers.<DataServer>any())).thenReturn(null);

        loginActivityPresenter.login(userProfile);

        verify(viewer).setStatusText(eq("user or password is invalid"));
    }

    @Test
    public void should_go_to_main_activity_when_login_successful() {
        UserProfile userProfile = mock(UserProfile.class);
        StorageRepository fileRepository = mock(StorageRepository.class);
        when(userProfile.login(Matchers.<NetworkInfo>any(), Matchers.<DataServer>any())).thenReturn(new UserResource("path"));
        when(userProfile.getUsername()).thenReturn("userA");
        when(viewer.getFileRepository(UserProfile.class)).thenReturn(fileRepository);
        when(viewer.getUsername()).thenReturn("userB");
        when(viewer.getPassword()).thenReturn("pwdB");

        loginActivityPresenter.login(userProfile);

        verify(userProfile).update(eq("userB"), eq("pwdB"));
        verify(fileRepository).saveData(userProfile);
        verify(viewer).startNextActivity(eq(MainActivity.class), Matchers.<StatusData>any());
        verify(viewer).closeActivity();
    }
}
