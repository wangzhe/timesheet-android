package com.tw.timesheet.android.storage;

import java.io.Serializable;

public interface FileStorage extends Serializable {

    public String getFileName();

    boolean isEmpty();
}
