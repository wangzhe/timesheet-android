package com.tw.timesheet.android.domain;

public class TimeSheetEvent {
    private String location;
    private String activityCode;
    private boolean billable;
    private String task;
    private boolean expense;
    private Week week;

    public TimeSheetEvent(String location, String activity, boolean billable, String task, String mon, String tue, String wed, String thu, String fri, String sat, String sun, boolean expense) {
        this.location = location;
        activityCode = activity;
        this.billable = billable;
        this.task = task;
        this.expense = expense;
        this.week = new Week(mon, tue, wed, thu, fri, sat, sun);
    }

    public String getLocation() {
        return location;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public boolean isBillable() {
        return billable;
    }

    public String getTask() {
        return task;
    }

    public boolean isExpense() {
        return expense;
    }

    public Week getWeek() {
        return week;
    }
}
