package com.tw.timesheet.android.util;

import org.json.JSONException;
import org.json.JSONObject;

import static com.tw.timesheet.android.util.StringUtil.EMPTY;

public class JSONUtil {

    public static String getJSONString(JSONObject jsonObject, String tag) throws JSONException {
        if (tag == null) return EMPTY;

        if (!jsonObject.isNull(tag)) {
            return StringUtil.copy(jsonObject.getString(tag));
        }
        String tagLowerCase = tag.toLowerCase();
        if (!jsonObject.isNull(tagLowerCase)) {
            return StringUtil.copy(jsonObject.getString(tagLowerCase));
        }
        return EMPTY;
    }
}
