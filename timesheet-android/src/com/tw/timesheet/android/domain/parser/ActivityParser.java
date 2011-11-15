package com.tw.timesheet.android.domain.parser;

import com.tw.timesheet.android.domain.TimeSheetEvent;
import com.tw.timesheet.android.util.JSONUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityParser {

    public TimeSheetEvent parse(JSONObject event) throws JSONException {
        String location = JSONUtil.getJSONString(event, "location");
        String activity = JSONUtil.getJSONString(event, "activity");
        boolean billable = JSONUtil.getJSONBoolean(event, "billable");
        String task = JSONUtil.getJSONString(event, "task");
        String mon = JSONUtil.getJSONString(event, "mon");
        String tue = JSONUtil.getJSONString(event, "tue");
        String wed = JSONUtil.getJSONString(event, "wed");
        String thu = JSONUtil.getJSONString(event, "thu");
        String fri = JSONUtil.getJSONString(event, "fri");
        String sat = JSONUtil.getJSONString(event, "sat");
        String sun = JSONUtil.getJSONString(event, "sun");
        boolean expense = JSONUtil.getJSONBoolean(event, "expense");

        return new TimeSheetEvent(location, activity, billable, task, mon, tue, wed, thu, fri, sat, sun, expense);
    }
}
