package com.tw.timesheet.android.presenter;

import android.app.Activity;
import android.net.NetworkInfo;
import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.*;
import com.tw.timesheet.android.system.DeviceSystem;
import com.tw.timesheet.android.widget.TimeSheetEntryView;
import com.tw.timesheet.android.widget.interfaces.ITimeSheetEntryView;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.tester.org.apache.http.HttpResponseStub;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class TimeSheetContentPresenterBackgroundTest {

    private TimeSheetContentViewStub viewer;
    private TimeSheetContentPresenter presenter;
    private DeviceSystem device;
    private StatusData statusData;

    @Before
    public void setUp() {
        viewer = new TimeSheetContentViewStub();
        device = mock(DeviceSystem.class);
        statusData = new StatusData(new UserProfile("userA", ""), new UserResource("/users"));
        presenter = new TimeSheetContentPresenter(viewer, device);
        presenter.setStatusData(statusData);
    }

    @Test
    public void should_add_correct_list_view_when_start_fetch_time_sheet_content() throws Exception {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(device.getActiveNetworkInfo()).thenReturn(networkInfo);

        Robolectric.getBackgroundScheduler().pause();
        Robolectric.addPendingHttpResponse(new TwHttpResponseStub(TwHttpResponseStub.TIME_SHEET_WITH_THREE_ENTRY));

        presenter.startFetchTimeSheetContent();

        Robolectric.getBackgroundScheduler().runOneTask();

        assertThat(viewer.summaryList.size(), is(3));
    }

    @Test
    public void should_add_nothing_to_list_view_when_fetch_none_time_sheet_content() throws Exception {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(device.getActiveNetworkInfo()).thenReturn(networkInfo);

        Robolectric.getBackgroundScheduler().pause();
        Robolectric.addPendingHttpResponse(new TwHttpResponseStub(TwHttpResponseStub.TIME_SHEET_WITH_NONE_ENTRY));

        presenter.startFetchTimeSheetContent();

        Robolectric.getBackgroundScheduler().runOneTask();

        assertThat(viewer.summaryList.size(), is(0));

    }

    @Test
    public void should_add_nothing_to_list_view_when_fetch_response_is_null() throws Exception {
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
        when(device.getActiveNetworkInfo()).thenReturn(networkInfo);

        Robolectric.getBackgroundScheduler().pause();
        Robolectric.addPendingHttpResponse(null);

        presenter.startFetchTimeSheetContent();

        Robolectric.getBackgroundScheduler().runOneTask();

        assertThat(viewer.summaryList.size(), is(0));

    }
    
    private class TimeSheetContentViewStub implements TimeSheetContentView {
        public List<TimeSheetEntry> summaryList;

        private TimeSheetContentViewStub() {
            this.summaryList = new ArrayList<TimeSheetEntry>();
        }

        @Override
        public void appendTimeSheetSummary(TimeSheetSummary summary) {
            for (int i = 0; i < summary.size(); i++) {
                summaryList.add(summary.getEntry(i));
            }
        }

        @Override
        public ITimeSheetEntryView createTimeSheetEntryView(TimeSheetEntry entry) {
            return new TimeSheetEntryView(new Activity(), entry);
        }
    }

    private class TwHttpResponseStub extends HttpResponseStub {

        public static final String TIME_SHEET_WITH_THREE_ENTRY = "test/com/tw/timesheet/android/json/time_sheet_response_validation.json";
        public static final String TIME_SHEET_WITH_NONE_ENTRY = "test/com/tw/timesheet/android/json/time_sheet_with_none_entry_response_validation.json";
        private String time_sheet_with_entry_file;

        protected TwHttpResponseStub(String time_sheet_with_entry_file) {
            this.time_sheet_with_entry_file = time_sheet_with_entry_file;
        }

        public StatusLine getStatusLine() {
            return new StatusLine() {
                @Override
                public ProtocolVersion getProtocolVersion() {
                    return null;
                }

                @Override
                public int getStatusCode() {
                    return 200;
                }

                @Override
                public String getReasonPhrase() {
                    return null;
                }
            };
        }

        @Override
        public HttpEntity getEntity() {
            return new HttpEntity() {
                @Override
                public boolean isRepeatable() {
                    return false;
                }

                @Override
                public boolean isChunked() {
                    return false;
                }

                @Override
                public long getContentLength() {
                    return 0;
                }

                @Override
                public Header getContentType() {
                    return null;
                }

                @Override
                public Header getContentEncoding() {
                    return null;
                }

                @Override
                public InputStream getContent() throws IOException, IllegalStateException {
                    return new FileInputStream(time_sheet_with_entry_file);
                }

                @Override
                public void writeTo(OutputStream outputStream) throws IOException {
                }

                @Override
                public boolean isStreaming() {
                    return false;
                }

                @Override
                public void consumeContent() throws IOException {
                }
            };
        }
    }
}
