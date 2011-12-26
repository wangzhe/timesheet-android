package com.tw.timesheet.android.domain;

import java.io.Serializable;
import java.util.List;

public class Setting implements Serializable {

    private boolean isDeptPS;
    private String country;
    private List<ScheduleNotification> scheduleList;

    public Setting(boolean deptPS, String country) {
        isDeptPS = deptPS;
        this.country = country;
    }

    public boolean isDeptPS() {
        return isDeptPS;
    }

    public void switchDept() {
        isDeptPS = !isDeptPS;
    }

    public List getScheduleList() {
        return scheduleList;
    }

    public ScheduleNotification createNotification() {
        return new ScheduleNotification();
    }
}
