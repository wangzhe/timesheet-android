package com.tw.timesheet.android.net;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TWTEHttpClientTest {

    @Test //TODO Not sure how to do this testing
    public void testExecute() {
        DefaultHttpClient httpClient = mock(DefaultHttpClient.class);
        HttpUriRequest request = mock(HttpUriRequest.class);
        TWTEHttpClient twteHttpClient = new TWTEHttpClient(httpClient);

        try {
            twteHttpClient.execute(null);
        } catch (Exception e) {

        }
    }
}
