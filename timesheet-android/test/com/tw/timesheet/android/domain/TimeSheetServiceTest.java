package com.tw.timesheet.android.domain;

import android.net.NetworkInfo;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.util.IOUtil;
import org.apache.http.client.methods.HttpPost;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimeSheetServiceTest {

    private TimeSheetService timesheet;
    private DataServer dataServer;
    private NetworkInfo networkInfo;
    private TimeSheetCondition condition;
    private UserResource userResource;

    @Before
    public void setUp() {
        timesheet = new TimeSheetService();
        dataServer = mock(DataServer.class);
        networkInfo = mock(NetworkInfo.class);
        userResource = new UserResource("base_uri");
    }

    @Test
    public void should_return_correct_size_with_correct_content_after_read_validate_time_sheet_json() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/time_sheet_response_validation.json");
        String response = IOUtil.getStringFromStream(fis);
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn(response);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);

        TimeSheetSummary timeSheetSummary = timesheet.fetch(networkInfo, dataServer, userResource);

        assertThat(timeSheetSummary.size(), is(3));
        assertThat(timeSheetSummary.refreshLink(), is("http://time.sheet.host/userA/self_link"));
        assertThat(timeSheetSummary.nextLink(), is("http://time.sheet.host/userA/next_link"));

        TimeSheetEntry entry = timeSheetSummary.getEntry(0);

        assertThat(entry.getBillableHours(), is("40.00"));
        assertThat(entry.getLastModified(), is("07-Nov-2011 @ 19:19:50"));
        assertThat(entry.getNonBillableHours(), is("6.00"));
        assertThat(entry.getWeekEndDate(), is("06-Nov-2011"));
        assertThat(entry.getTotalHours(), is("46.00"));
        assertThat(entry.getStatus(), is("submitted"));
        assertThat(entry.getSubmissionDate(), is("07-Nov-2011 @ 19:19:50"));

        TimeSheetEvent timeSheetEvent = entry.getTimeSheetEvent(0);

        assertThat(timeSheetEvent.getLocation(), is("CHN"));
        assertThat(timeSheetEvent.getActivityCode(), is("REA0001 ANDR_P2 MISC"));
        assertThat(timeSheetEvent.isBillable(), is(true));
        assertThat(timeSheetEvent.getTask(), is("description"));
        assertThat(timeSheetEvent.isExpense(), is(true));

        Week week = timeSheetEvent.getWeek();

        assertThat(week.getMon(), is("8"));
        assertThat(week.getTue(), is("8"));
        assertThat(week.getWed(), is("8"));
        assertThat(week.getThu(), is("8"));
        assertThat(week.getFri(), is("8"));
        assertThat(week.getSat(), is("0"));
        assertThat(week.getSun(), is("0"));
    }

    @Test
    public void should_return_null_when_data_server_had_empty_response(){
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn(null);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);

        TimeSheetSummary timeSheetSummary = timesheet.fetch(networkInfo, dataServer, userResource);

        assertThat(timeSheetSummary, IsNull.<Object>nullValue());
    }

    @Test
    public void should_return_null_when_network_is_broken(){
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn("something");
        when(networkInfo.isConnectedOrConnecting()).thenReturn(false);

        TimeSheetSummary timeSheetSummary = timesheet.fetch(networkInfo, dataServer, userResource);

        assertThat(timeSheetSummary, IsNull.<Object>nullValue());
    }

    @Test
    public void should_return_null_when_network_is_null(){
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn("something");

        TimeSheetSummary timeSheetSummary = timesheet.fetch(null, dataServer, userResource);

        assertThat(timeSheetSummary, IsNull.<Object>nullValue());
    }

    @Test
    @Ignore("no finished")
    public void should_return_correct_size_when_fetch_data_with_data_duration() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/time_sheet_response_validation.json");
        String response = IOUtil.getStringFromStream(fis);

//        new TimeSheetService().TimeSheetSummaryParser(response);

//        timesheet.fetch(networkInfo, dataServer, condition);
    }

    @Test
    @Ignore("no finished")
    public void should_request_correct_site_when_do_refresh() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/time_sheet_response_validation.json");
        String response = IOUtil.getStringFromStream(fis);
        when(dataServer.postHttpRequest(Matchers.<HttpPost>any())).thenReturn(response);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);

        TimeSheetSummary timeSheetSummary = timesheet.fetch(networkInfo, dataServer, userResource);
//        timesheet.refresh();
//
//        verify()
//        verify(dataServer.postHttpRequest())
    }

    @Test
    @Ignore("no finished")
    public void should_return_more_entries_when_do_show_more() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/time_sheet_response_validation.json");
        String response = IOUtil.getStringFromStream(fis);

//        new TimeSheetService().TimeSheetSummaryParser(response);

//        timesheet.showmore();
    }
}
