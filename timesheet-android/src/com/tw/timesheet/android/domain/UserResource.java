package com.tw.timesheet.android.domain;

import com.tw.timesheet.android.util.JSONUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class UserResource {

    private String baseURI;

    public UserResource(String source) {
        baseURI = source;
    }

    public String getURI(String requireURI) {
        return baseURI + requireURI;
    }

    public static class UserResourceParser {

        public UserResource parse(String content) {
            if (content == null) return null;
            UserResource userResource = null;
            try {
                userResource = new UserResource(JSONUtil.getJSONString(new JSONObject(content), "source"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return userResource;
        }
    }
}
