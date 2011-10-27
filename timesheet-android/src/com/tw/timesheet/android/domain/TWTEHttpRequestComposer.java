package com.tw.timesheet.android.domain;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import java.io.UnsupportedEncodingException;

public class TWTEHttpRequestComposer {

    public static final String SEARCH_HEADER_ACCEPT = "application/vnd.tw.tesearchresults+json";
    public static final String SEARCH_HEADER_ACCEPT_ENCODING = "gzip, deflate";
    public static final String SEARCH_HEADER_CONTENT_TYPE = "application/vnd.tw.insert.doc+json";

    public HttpPost getPostRequest(String url, String content, String encoding) {
        HttpPost request = null;
        try {
            request = new HttpPost(encodeUri(url));
            setEntity(request, content, encoding);
            setHeaders(request, new BasicHeader("accept", SEARCH_HEADER_ACCEPT),
                    new BasicHeader("accept-encoding", SEARCH_HEADER_ACCEPT_ENCODING),
                    new BasicHeader("content-type", SEARCH_HEADER_CONTENT_TYPE));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return request;
    }

    private void setHeaders(HttpRequestBase request, BasicHeader... httpHeaders) {
        request.setHeaders(httpHeaders);
    }

    private void setEntity(HttpPost postRequest, String content, String encoding) throws UnsupportedEncodingException {
        postRequest.setEntity(new StringEntity(content, encoding));
    }

    private String encodeUri(String url) {
        return url.replace(" ", "%20");
    }

}
