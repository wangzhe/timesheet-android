package com.tw.timesheet.android.domain;

import com.tw.timesheet.android.net.DataServer;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import java.io.UnsupportedEncodingException;

public class TWTEHttpRequestComposer {

    public static final String LOGIN_HEADER_ACCEPT = "application/vnd.tw.te.userresults+json";
    public static final String LOGIN_HEADER_ACCEPT_ENCODING = "gzip, deflate";
    public static final String LOGIN_HEADER_CONTENT_TYPE = "application/vnd.tw.te.user.doc+json";


    public static final String TIME_SHEET_SEARCH_HEADER_ACCEPT = "application/vnd.tw.te.searchresults+json";
    public static final String TIME_SHEET_SEARCH_HEADER_ACCEPT_ENCODING = "gzip, deflate";
    public static final String TIME_SHEET_SEARCH_HEADER_CONTENT_TYPE = "application/vnd.tw.te.search.doc+json";

    public HttpPost createLoginRequest(String url, String content, String encoding) {
        HttpPost request = null;
        try {
            request = new HttpPost(encodeUri(DataServer.SERVER_ADDRESS + url));
            setHeaders(request, new BasicHeader("accept", LOGIN_HEADER_ACCEPT),
                    new BasicHeader("accept-encoding", LOGIN_HEADER_ACCEPT_ENCODING),
                    new BasicHeader("content-type", LOGIN_HEADER_CONTENT_TYPE));
            setEntity(request, content, encoding);
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

    public HttpPost createTimeSheetRequest(String url) {
        HttpPost request = new HttpPost(encodeUri(DataServer.SERVER_ADDRESS + url));
        setHeaders(request, new BasicHeader("accept", TIME_SHEET_SEARCH_HEADER_ACCEPT),
                new BasicHeader("accept-encoding", TIME_SHEET_SEARCH_HEADER_ACCEPT_ENCODING),
                new BasicHeader("content-type", TIME_SHEET_SEARCH_HEADER_CONTENT_TYPE));
        return request;
    }

    public HttpGet createTimeSheetRequestGet(String url) {
        HttpGet request = new HttpGet(encodeUri(DataServer.SERVER_ADDRESS + url));
        setHeaders(request, new BasicHeader("accept", TIME_SHEET_SEARCH_HEADER_ACCEPT),
                new BasicHeader("accept-encoding", TIME_SHEET_SEARCH_HEADER_ACCEPT_ENCODING),
                new BasicHeader("content-type", TIME_SHEET_SEARCH_HEADER_CONTENT_TYPE));
        return request;
    }
}
