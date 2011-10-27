package com.tw.timesheet.android.util;

public class StringUtil {
    public static final String EMPTY = "";

    public static boolean isEmpty(String text) {
        return (text == null) || text.trim().equals("");
    }

    public static String copy(String original) {
        if (isEmpty(original))
            return EMPTY;
        return new String(original.toCharArray());
    }
}
