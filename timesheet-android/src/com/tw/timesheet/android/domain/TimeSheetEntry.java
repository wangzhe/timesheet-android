package com.tw.timesheet.android.domain;

import java.util.List;

public class TimeSheetEntry {

    private String weekEndDate;
    private String lastModified;
    private String billableHours;
    private String nonBillableHours;
    private String totalHours;
    private String status;
    private String submissionDate;
    private List<TimeSheetEvent> timeSheetEvents;

    public TimeSheetEntry(String weekEndDate, String lastModified, String billableHours, String nonBillableHours, String totalHours, String status, String submissionDate) {
        this.weekEndDate = weekEndDate;
        this.lastModified = lastModified;
        this.billableHours = billableHours;
        this.nonBillableHours = nonBillableHours;
        this.totalHours = totalHours;
        this.status = status;
        this.submissionDate = submissionDate;
    }

    public void addEvents(List<TimeSheetEvent> timeSheetEvents) {
        this.timeSheetEvents = timeSheetEvents;
    }

    public String getWeekEndDate() {
        return weekEndDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getBillableHours() {
        return billableHours;
    }

    public String getNonBillableHours() {
        return nonBillableHours;
    }

    public String getStatus() {
        return status;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public TimeSheetEvent getTimeSheetEvent(int index) {
        return timeSheetEvents.get(index);
    }
}
