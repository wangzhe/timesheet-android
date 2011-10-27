package com.tw.timesheet.android.util;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.tw.timesheet.android.util.JSONUtil.getJSONString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(RobolectricTestRunner.class)
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
    public void testGetJSONString() throws Exception {
        assertThat(getJSONString(jsonObject, "string"), is("string_value"));
    }
}
