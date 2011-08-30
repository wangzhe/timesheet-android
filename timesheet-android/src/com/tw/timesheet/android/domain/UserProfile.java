package com.tw.timesheet.android.domain;

public class UserProfile {

    private static UserProfile userProfile = null;
    private String username = "";
    private String password = "";

    public UserProfile() {
        if(!startFileUtil()) return;
        username = this.getLocalUsername();
        password = this.getLocalPassword();
    }

    public static UserProfile getUserProfile() {
        return (userProfile == null) ? new UserProfile() : userProfile;
    }

    public boolean isLoginSuccessful() {
        //checkNetworkConnection
        //checkNetworkLogin
        return true;
    }

    public boolean hasDefaultSetting() {
        //checkFirstTimeStartActivity
        return true;
    }

    public String getUsername() {
        return username;
    }

    private String getLocalPassword() {
        return "r0ys";
    }

    private String getLocalUsername() {
        return "tw";
    }

    private boolean startFileUtil() {
        return true;
    }
}
