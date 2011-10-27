package com.tw.timesheet.android.domain;

import android.net.NetworkInfo;
import com.tw.timesheet.android.exception.ConnectionTimeoutException;
import com.tw.timesheet.android.net.DataServer;
import com.tw.timesheet.android.storage.FileStorage;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;

public class UserProfile implements FileStorage {

    private String username;
    private String password;
    private boolean hasDefaultSetting;

    public UserProfile() {
        username = "";
        password = "";
    }

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public NetworkResource login(NetworkInfo network, DataServer dataServer) {
        if (isOffline(network)) return null;
        String response;
        NetworkResource networkResource;
        try {
            HttpPost request = new TWTEHttpRequestComposer().getPostRequest("", "", HTTP.UTF_8);
            response = dataServer.postHttpRequest(request);
            networkResource = new NetworkResource.NetworkResourceParser().parse(response);
        } catch (ConnectionTimeoutException cte) {
            cte.printStackTrace();
            return null;
        } 
        return networkResource;
    }

    private boolean isOffline(NetworkInfo network) {
        return network == null || !network.isConnectedOrConnecting();
    }

    public boolean hasDefaultSetting() {
        return !"".equalsIgnoreCase(username) && !"".equalsIgnoreCase(password);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getFileName() {
        return "user_profile_data";
    }

    @Override
    public boolean isEmpty() {
        return (username == "" || password == "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }
}
