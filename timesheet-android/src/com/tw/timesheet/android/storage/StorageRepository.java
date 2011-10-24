package com.tw.timesheet.android.storage;

public interface StorageRepository<T extends FileStorage> {

    T loadData(T storage);
}
