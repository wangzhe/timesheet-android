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

    public static float getJSONFloat(JSONObject jsonObject, String tag) throws JSONException {
        float result = -1;
        if (tag == null) return result;
        if (!jsonObject.isNull(tag)) {
            result = (float) jsonObject.getDouble(tag);
            return result;
        }
        String tagLowerCase = tag.toLowerCase();
        if (!jsonObject.isNull(tagLowerCase)) {
            result = (float) jsonObject.getDouble(tagLowerCase);
            return result;
        }
        return result;
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String tag) throws JSONException {
        JSONObject result = null;
        if (tag == null) return result;
        if (!jsonObject.isNull(tag)) {
            result = jsonObject.getJSONObject(tag);
            return result;
        }
        String tagLowerCase = tag.toLowerCase();
        if (!jsonObject.isNull(tagLowerCase)) {
            result = jsonObject.getJSONObject(tagLowerCase);
            return result;
        }
        return result;
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

    public static int getJSONInt(JSONObject jsonObject, String tag) throws JSONException {
        int result = -1;
        if (tag == null) return result;
        if (!jsonObject.isNull(tag)) {
            result = jsonObject.getInt(tag);
            return result;
        }
        String tagLowerCase = tag.toLowerCase();
        if (!jsonObject.isNull(tagLowerCase)) {
            result = jsonObject.getInt(tagLowerCase);
            return result;
        }
        return result;
    }

    public static boolean hasElement(JSONObject generalObject, String targetingTag) {
        if (targetingTag == null) return false;
        String tagLowerCase = targetingTag.toLowerCase();
        return generalObject.has(targetingTag) || generalObject.has(tagLowerCase);
    }
}
