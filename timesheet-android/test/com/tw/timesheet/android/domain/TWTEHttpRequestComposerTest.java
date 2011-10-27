package com.tw.timesheet.android.domain;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TWTEHttpRequestComposerTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void should_replace_space_signal_mark() throws UnsupportedEncodingException {
        HttpPost request = new TWTEHttpRequestComposer().getPostRequest("space to space", "content", HTTP.UTF_8);
        assertThat(request.getURI().toString(), is("space%20to%20space"));
    }

    @Test
    public void should_contain_header_infos() throws UnsupportedEncodingException {
        HttpPost request = new TWTEHttpRequestComposer().getPostRequest("space to space", "content", HTTP.UTF_8);

        assertThat(request.getFirstHeader("accept").getValue(), is("application/vnd.tw.tesearchresults+json"));
        assertThat(request.getFirstHeader("accept-encoding").getValue(), is("gzip, deflate"));
        assertThat(request.getFirstHeader("content-type").getValue(), is("application/vnd.tw.insert.doc+json"));
    }

    @Test
    public void should_contain_content_infos() throws IOException {
        HttpPost request = new TWTEHttpRequestComposer().getPostRequest("space to space", "content", HTTP.UTF_8);

        byte[] buff = new byte[1024];
        request.getEntity().getContent().read(buff);
        String content = "";
        for (byte b : buff) {
            content += (char) b;
        }
        assertThat(content.trim(), is("content"));
    }

    @Test
    public void should_return_null_when_encounter_unsupported_encoding_exception() {
        HttpPost request = new TWTEHttpRequestComposer().getPostRequest("space to space", "content", "xxx");

        assertThat(request, IsNull.<Object>nullValue());
    }


}
