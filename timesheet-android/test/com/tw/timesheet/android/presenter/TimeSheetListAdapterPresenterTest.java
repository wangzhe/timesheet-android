package com.tw.timesheet.android.presenter;

import com.tw.timesheet.android.activity.callback.TimeSheetContentView;
import com.tw.timesheet.android.domain.TimeSheetEntry;
import com.tw.timesheet.android.domain.TimeSheetSummary;
import com.tw.timesheet.android.util.IOUtil;
import com.tw.timesheet.android.widget.interfaces.ITimeSheetEntryView;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimeSheetListAdapterPresenterTest {
    private TimeSheetContentView view;
    private TimeSheetSummary summary;
    private TimeSheetListAdapterPresenter adapterPresenter;
    private TimeSheetEntryViewStub viewStub;

    @Before
    public void setUp() throws Exception {
        view = mock(TimeSheetContentView.class);
        createTimeSheetSummary();

        adapterPresenter = new TimeSheetListAdapterPresenter(view,summary);
    }

    private void createTimeSheetSummary() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/time_sheet_response_validation.json");
        String response = IOUtil.getStringFromStream(fis);
        summary = new TimeSheetSummary.TimeSheetSummaryParser().parse(response);
    }

    @Test
    public void test_should_create_entry_view_when_adapter_current_view_is_null() throws Exception {
        viewStub = new TimeSheetEntryViewStub(summary.getEntry(0));
        when(view.createTimeSheetEntryView(Matchers.<TimeSheetEntry>any())).thenReturn(viewStub);
        ITimeSheetEntryView entry_view = adapterPresenter.createTimeSheetEntryView(0, null);
        
        assertThat(entry_view, IsNull.<ITimeSheetEntryView>notNullValue());
    }

    @Test
    public void test_should_reuse_view_and_update_date_when_adapter_current_view_is_not_null() throws Exception {
        viewStub = new TimeSheetEntryViewStub(summary.getEntry(0));
        when(view.createTimeSheetEntryView(Matchers.<TimeSheetEntry>any())).thenReturn(viewStub);
        TimeSheetEntryViewStub entryView = (TimeSheetEntryViewStub) adapterPresenter.createTimeSheetEntryView(0, null);

        assertThat(entryView.entry.getNonBillableHours(), is("6.00"));

        TimeSheetEntryViewStub newEntryView = (TimeSheetEntryViewStub) adapterPresenter.createTimeSheetEntryView(1, entryView);

        assertThat(newEntryView, is(entryView));
        assertThat(newEntryView.entry.getNonBillableHours(), is("0.00"));
    }

    class TimeSheetEntryViewStub implements ITimeSheetEntryView {

        protected TimeSheetEntry entry;

        public TimeSheetEntryViewStub(TimeSheetEntry entry) {
            this.entry = entry;
        }

        @Override
        public void update(TimeSheetEntry entry) {
            this.entry = entry;
        }
    }
}
