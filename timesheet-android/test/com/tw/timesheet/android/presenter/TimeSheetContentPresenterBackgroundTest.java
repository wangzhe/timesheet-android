package com.tw.timesheet.android.presenter;

import android.net.NetworkInfo;
import android.widget.ListView;
import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.StatusData;
import com.tw.timesheet.android.domain.UserResource;
import com.tw.timesheet.android.system.DeviceSystem;
import com.tw.timesheet.android.util.IOUtil;
import com.tw.timesheet.android.widget.TimeSheetEntryView;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class TimeSheetContentPresenterBackgroundTest {

    private TestTimeSheetContentView viewer;
    private TimeSheetContentPresenter presenter;
    private DeviceSystem device;
    private StatusData statusData;

    @Before
    public void setUp() {
        viewer = new TestTimeSheetContentView();
        device = mock(DeviceSystem.class);
        statusData = new StatusData("userA", new UserResource("users/"));
        presenter = new TimeSheetContentPresenter(viewer, device);
        presenter.setStatusData(statusData);
    }

    @Test
    public void should_add_correct_list_view_when_start_fetch_time_sheet_content() throws Exception {
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/time_sheet_response_validation.json");
        String response = IOUtil.getStringFromStream(fis);
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(device.getActiveNetworkInfo()).thenReturn(networkInfo);

        Robolectric.getBackgroundScheduler().pause();
        Robolectric.addPendingHttpResponse(200, response);

        presenter.startFetchTimeSheetContent();

        Robolectric.getBackgroundScheduler().runOneTask();

        assertThat(viewer.summaryList.getCount(), is(3));
    }

    private class TestTimeSheetContentView implements TimeSheetContentView {
        public ListView summaryList;

        @Override
        public void appendTimeSheetEntry(TimeSheetEntryView entryView) {
            summaryList.addView(entryView);
        }
    }
}
