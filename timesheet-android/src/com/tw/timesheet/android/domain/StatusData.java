package com.tw.timesheet.android.domain;

import java.io.Serializable;

public class StatusData implements Serializable {

    private String username;
    private UserResource userResource;

    public StatusData(String username) {
        this.username = username;
    }

    public StatusData(String username, UserResource userResource) {
        this.username = username;
        this.userResource = userResource;
    }

    public String getUsername() {
        return username;
    }

    public UserResource getUserResource() {
        return userResource;
    }
}
