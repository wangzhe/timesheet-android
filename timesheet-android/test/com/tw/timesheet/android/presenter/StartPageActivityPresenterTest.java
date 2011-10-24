package com.tw.timesheet.android.presenter;

import android.net.NetworkInfo;
import com.tw.timesheet.android.activity.LoginActivity;
import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.activity.callback.StartPageActivityView;
import com.tw.timesheet.android.domain.UserProfile;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.storage.FileRepository;
import com.tw.timesheet.android.storage.FileStorage;
import com.tw.timesheet.android.storage.StorageRepository;
import com.tw.timesheet.android.system.DeviceSystem;
import org.h2.engine.Setting;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Mockito.*;

public class StartPageActivityPresenterTest {

    private StartPageActivityView view;
    private StartPageActivityPresenter presenter;
    private DeviceSystem device;

    @Before
    public void setUp() {
        view = mock(StartPageActivityView.class);
        device = mock(DeviceSystem.class);
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(device.getActiveNetworkInfo()).thenReturn(networkInfo);
        presenter = new StartPageActivityPresenter(view, device);
    }

    @Test
    public void should_load_user_profile_data_then_start_next_activity_when_start_app() {
        StorageRepository storageRepository = mock(FileRepository.class);
        when(storageRepository.loadData(Matchers.<FileStorage>any())).thenReturn(new UserProfile());

        presenter.onAppStart(storageRepository);

        verify(storageRepository).loadData(Matchers.<FileStorage>any());
        verify(view).startNextActivity(Matchers.<Class>any(), Matchers.<String>any());
    }

    @Test
    public void should_start_log_in_activity_when_start_app_and_user_log_in_failed() {
        FileStorage userProfile = mock(UserProfile.class);
        StorageRepository storageRepository = mock(FileRepository.class);
        when(((UserProfile) userProfile).login(Matchers.<NetworkInfo>anyObject(), Matchers.<DataServer>anyObject())).thenReturn(false);
        when(storageRepository.loadData(Matchers.<FileStorage>any())).thenReturn(userProfile);

        presenter.onAppStart(storageRepository);

        verify(view).startNextActivity(LoginActivity.class, eq(Matchers.<String>any()));
        verify(view).closeActivity();
    }

    @Test
    public void should_start_setting_activity_when_start_app_and_user_log_in_successful_but_no_default_setting() {
        FileStorage userProfile = mock(UserProfile.class);
        StorageRepository storageRepository = mock(FileRepository.class);
        when(((UserProfile) userProfile).login(Matchers.<NetworkInfo>any(), Matchers.<DataServer>anyObject())).thenReturn(true);
        when(((UserProfile) userProfile).hasDefaultSetting()).thenReturn(false);
        when(storageRepository.loadData(Matchers.<FileStorage>any())).thenReturn(userProfile);

        presenter.onAppStart(storageRepository);

        verify(view).startNextActivity(Setting.class, eq(Matchers.<String>any()));
    }

    @Test
    public void should_start_main_activity_when_start_app_and_user_log_in_successful_and_has_default_setting() {
        FileStorage userProfile = mock(UserProfile.class);
        StorageRepository storageRepository = mock(FileRepository.class);
        when(((UserProfile) userProfile).login(Matchers.<NetworkInfo>any(), Matchers.<DataServer>anyObject())).thenReturn(false);
        when(storageRepository.loadData(Matchers.<FileStorage>any())).thenReturn(userProfile);

        presenter.onAppStart(storageRepository);

        verify(view).startNextActivity(MainActivity.class, eq(Matchers.<String>any()));
    }
}
