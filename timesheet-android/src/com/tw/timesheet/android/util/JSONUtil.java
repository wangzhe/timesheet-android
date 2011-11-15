package com.tw.timesheet.android.util;

import org.json.JSONArray;
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

    public static JSONArray getJSONArray(JSONObject jsonObject, String tag) throws JSONException {
        JSONArray result = null;
        if (jsonObject == null || tag == null) {
            return result;
        }
        if (!jsonObject.isNull(tag)) {
            result = jsonObject.getJSONArray(tag);
            return result;
        }
        String tagLowerCase = tag.toLowerCase();
        if (!jsonObject.isNull(tagLowerCase)) {
            result = jsonObject.getJSONArray(tagLowerCase);
            return result;
        }
        return null;
    }

    public static boolean getJSONBoolean(JSONObject jsonObject, String tag) throws JSONException {
        boolean result = false;
        if (tag == null) return result;
        if (!jsonObject.isNull(tag)) {
            result = jsonObject.getBoolean(tag);
            return result;
        }
        String tagLowerCase = tag.toLowerCase();
        if (!jsonObject.isNull(tagLowerCase)) {
            result = jsonObject.getBoolean(tagLowerCase);
            return result;
        }
        return result;
    }
}
