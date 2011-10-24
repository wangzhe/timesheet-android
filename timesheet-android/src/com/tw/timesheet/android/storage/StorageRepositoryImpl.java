package com.tw.timesheet.android.storage;

import android.content.Context;

public abstract class StorageRepositoryImpl<T extends FileStorage> implements StorageRepository<T> {

    private Context context;

    public StorageRepositoryImpl(Context context) {
        this.context = context;
    }

    public Context getApplicationContext() {
        return context;
    }
}
