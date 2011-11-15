package com.tw.timesheet.android.background;

import android.os.AsyncTask;
import com.tw.timesheet.android.domain.TimeSheetSummary;
import com.tw.timesheet.android.presenter.TimeSheetContentPresenter;

public class TimeSheetContentTask extends AsyncTask<Void, Void, TimeSheetSummary> {

    private TimeSheetContentPresenter presenter;

    public TimeSheetContentTask(TimeSheetContentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected TimeSheetSummary doInBackground(Void... params) {
        return presenter.fetchTimeSheetContent();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(TimeSheetSummary timeSheetSummary) {
        presenter.updateTimeSheetContent(timeSheetSummary);
    }
}
