package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.MainActivity;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserProfile;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainActivityPresenterTest {

    private MainActivity viewer;
    private MainActivityPresenter mainActivityPresenter;

    @Before
    public void setUp() throws Exception {
        viewer = mock(MainActivity.class);
        mainActivityPresenter = new MainActivityPresenter(viewer);
    }

    @Test
    public void should_set_proper_text_when_init() {
        mainActivityPresenter.setStatusData(new StatusData(new UserProfile("userA"), null));

        mainActivityPresenter.initUI();

        verify(viewer).setTitleText(eq("Hello userA, this is Main View"));
    }


}
