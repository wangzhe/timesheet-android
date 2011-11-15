package com.tw.timesheet.android.domain;

import com.tw.timesheet.android.domain.parser.ActivityParser;
import com.tw.timesheet.android.util.JSONUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeSheetSummary implements Serializable {

    List<TimeSheetEntry> timeSheetEntries = new ArrayList<TimeSheetEntry>();
    private String self;
    private String next;

    public TimeSheetSummary(List<TimeSheetEntry> timeSheetEntries, String self, String next) {
        this.timeSheetEntries = timeSheetEntries;
        this.self = self;
        this.next = next;
    }

    public int size() {
        return timeSheetEntries.size();
    }

    public TimeSheetEntry getEntry(int index) {
        return timeSheetEntries.get(index);
    }

    public String refreshLink() {
        return self;
    }

    public String nextLink() {
        return next;
    }

    public static class TimeSheetSummaryParser {

        private ActivityParser activityParser = new ActivityParser();

        public TimeSheetSummary parse(String content) {
            if (content == null) return null;
            TimeSheetSummary timeSheetSummary = null;
            try {
                timeSheetSummary = new TimeSheetSummary(getTimeSheetEntries(content), getLink(content, "self"), getLink(content, "next"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return timeSheetSummary;
        }

        private String getLink(String content, String linkTag) throws JSONException {
            String link = null;
            JSONArray linkArray = JSONUtil.getJSONArray(new JSONObject(content), "links");
            for (int i = 0; i < linkArray.length(); i++) {
                JSONObject linkObject = linkArray.getJSONObject(i);
                if(linkTag.equalsIgnoreCase(JSONUtil.getJSONString(linkObject, "rel"))) {
                    link = JSONUtil.getJSONString(linkObject, "uri");
                    break;
                }
            }
            return link;
        }

        private List<TimeSheetEntry> getTimeSheetEntries(String content) throws JSONException {
            List<TimeSheetEntry> timeSheetEntries = new ArrayList<TimeSheetEntry>();
            JSONArray results = JSONUtil.getJSONArray(new JSONObject(content), "results");
            for (int i = 0; i < results.length(); i++) {
                TimeSheetEntry entry = getTimeSheetEntry(results.getJSONObject(i));
                timeSheetEntries.add(entry);
            }
            return timeSheetEntries;
        }

        private TimeSheetEntry getTimeSheetEntry(JSONObject result) throws JSONException {
            String weekEndDate = JSONUtil.getJSONString(result, "week-end-date");
            String lastModified = JSONUtil.getJSONString(result, "last-modified");
            String billableHours = JSONUtil.getJSONString(result, "billable-hours");
            String nonBillableHours = JSONUtil.getJSONString(result, "non-billable-hours");
            String totalHours = JSONUtil.getJSONString(result, "totle-hours");
            String status = JSONUtil.getJSONString(result, "status");
            String submissionDate = JSONUtil.getJSONString(result, "date");

            TimeSheetEntry entry = new TimeSheetEntry(weekEndDate, lastModified, billableHours, nonBillableHours, totalHours, status, submissionDate);
            entry.addEvents(getTimeSheetEvents(result.getJSONArray("activities")));

            return entry;
        }

        private List<TimeSheetEvent> getTimeSheetEvents(JSONArray events) throws JSONException {
            ArrayList<TimeSheetEvent> timeSheetEvents = new ArrayList<TimeSheetEvent>();
            for (int i = 0; i < events.length(); i++) {
                JSONObject event = events.getJSONObject(i);
                timeSheetEvents.add(activityParser.parse(event));
            }
            return timeSheetEvents;
        }
    }
}
