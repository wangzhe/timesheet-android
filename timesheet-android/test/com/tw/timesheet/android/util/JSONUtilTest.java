package com.tw.timesheet.android.util;

import org.hamcrest.core.IsNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.tw.timesheet.android.util.JSONUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JSONUtilTest {

    private String response;
    private JSONObject jsonObject;

    @Before
    public void setUp() throws FileNotFoundException, JSONException {
        FileInputStream fis = new FileInputStream("test/com/tw/timesheet/android/json/json_validation_test.json");
        response = IOUtil.getStringFromStream(fis);
        jsonObject = new JSONObject(response);
    }

    @Test
    public void should_return_null_when_array_tag_is_none() throws Exception {
        assertThat(getJSONArray(jsonObject, null), IsNull.<Object>nullValue());
    }

    @Test
    public void should_return_expect_array_list_when_array_tag_found() throws Exception {
        JSONArray list = getJSONArray(jsonObject, "array");
        assertThat(list.length(), is(3));

        JSONObject entry = (JSONObject) list.get(0);
        assertThat(getJSONString(entry, "array_a"), is("value_a"));
        entry = (JSONObject) list.get(1);
        assertThat(getJSONString(entry, "array_b"), is("value_b"));
        entry = (JSONObject) list.get(2);
        assertThat(getJSONString(entry, "array_c"), is("value_c"));
    }

    @Test
    public void should_return_expect_string_when_array_tag_low_case_found() throws Exception {
        JSONArray list = getJSONArray(jsonObject, "aRRay");
        assertThat(list.length(), is(3));
    }

    @Test
    public void should_return_null_when_array_tag_not_found() throws Exception {
        assertThat(getJSONArray(jsonObject, "non_existed_tag"), IsNull.<Object>nullValue());
    }

    @Test
    public void should_return_empty_when_tag_is_none() throws Exception {
        assertThat(getJSONString(jsonObject, null), is(StringUtil.EMPTY));
    }

    @Test
    public void should_return_expect_string_when_tag_found() throws Exception {
        assertThat(getJSONString(jsonObject, "string"), is("string_value"));
    }

    @Test
    public void should_return_expect_string_when_tag_low_case_found() throws Exception {
        assertThat(getJSONString(jsonObject, "sTRing"), is("string_value"));
    }

    @Test
    public void should_return_expect_string_when_tag_not_found() throws Exception {
        assertThat(getJSONString(jsonObject, "non_existed_tag"), is(StringUtil.EMPTY));
    }


    @Test
    public void should_return_false_when_boolean_tag_is_none() throws Exception {
        assertThat(getJSONBoolean(jsonObject, null), is(false));
    }

    @Test
    public void should_return_expect_value_when_boolean_tag_found() throws Exception {
        assertThat(getJSONBoolean(jsonObject, "boolean"), is(true));
    }

    @Test
    public void should_return_expect_string_when_boolean_tag_low_case_found() throws Exception {
        assertThat(getJSONBoolean(jsonObject, "booLEan"), is(true));
    }

    @Test
    public void should_return_false_when_boolean_tag_not_found() throws Exception {
        assertThat(getJSONBoolean(jsonObject, "non_existed_tag"), is(false));
    }
}
