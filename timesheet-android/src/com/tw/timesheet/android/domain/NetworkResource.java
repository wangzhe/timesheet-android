package com.tw.timesheet.android.domain;

import com.tw.timesheet.android.util.JSONUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkResource {

    private String baseURI;

    public NetworkResource(String source) {
        baseURI = source;
    }

    public String getURI(String requireURI) {
        return baseURI + requireURI;
    }

    public static class NetworkResourceParser {

        public NetworkResource parse(String content) {
            if (content == null) return null;
            NetworkResource networkResource = null;
            try {
                networkResource = new NetworkResource(JSONUtil.getJSONString(new JSONObject(content), "source"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return networkResource;
        }
    }
}
