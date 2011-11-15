package com.tw.timesheet.android.net;

import com.tw.timesheet.android.builder.TWTEHttpClientBuilder;
import com.tw.timesheet.android.domain.TWTEHttpRequestComposer;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataServerTest {

    private TWTEHttpClient httpClient;
    private DataServer dataServer;
    private HttpPost request;
    private HttpResponse httpResponse;

    @Before
    public void setUp() throws Exception {
        httpResponse = mock(HttpResponse.class);
        httpClient = new TWTEHttpClientBuilder().response(httpResponse).build();
        dataServer = DataServer.createDataServer(httpClient);
        request = new TWTEHttpRequestComposer().createLoginRequest("http://te.thoughtworks.com/project?start=proj te 11", "content", HTTP.UTF_8);
    }

    @Test
    public void should_return_expect_response_when_do_post_request() throws Exception {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpResponse.getEntity()).thenReturn(new StringEntity("project_te_11"));

        String response = dataServer.postHttpRequest(request);

        assertThat(response, is("project_te_11"));

        when(statusLine.getStatusCode()).thenReturn(201);
        response = dataServer.postHttpRequest(request);
        assertThat(response, is("project_te_11"));
    }

    @Test
    public void should_return_null_when_do_post_request_and_get_incorrect_code() throws Exception {
        StatusLine statusLine = mock(StatusLine.class);
        when(statusLine.getStatusCode()).thenReturn(404);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpResponse.getEntity()).thenReturn(new StringEntity("project_te_11"));

        String response = dataServer.postHttpRequest(request);

        assertThat(response, IsNull.<Object>nullValue());
    }
}
