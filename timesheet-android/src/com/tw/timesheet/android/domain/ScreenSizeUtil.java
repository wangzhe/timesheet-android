package com.tw.timesheet.android.domain;

public class ScreenSizeUtil {


    public static int getPxFromDp(float scale, float dpValue) {
        return (int) (dpValue * scale + 0.5f);
    }
}
