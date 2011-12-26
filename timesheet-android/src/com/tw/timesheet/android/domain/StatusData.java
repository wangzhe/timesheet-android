package com.tw.timesheet.android.domain;

import java.io.Serializable;

public class StatusData implements Serializable {

    private UserResource userResource;
    private UserProfile userProfile;

    public StatusData(UserProfile userProfile, UserResource userResource) {
        this.userProfile = (userProfile == null) ? new UserProfile() : userProfile;
        this.userResource = userResource;
    }

    public String getUsername() {
        return userProfile.getUsername();
    }

    public UserResource getUserResource() {
        return userResource;
    }

    public Setting getSetting() {
        return userProfile.getSetting();
    }
}
