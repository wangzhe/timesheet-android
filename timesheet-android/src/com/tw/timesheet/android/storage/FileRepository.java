package com.tw.timesheet.android.storage;

import android.content.Context;
import com.tw.timesheet.android.util.IOUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileRepository<T extends FileStorage> extends StorageRepositoryImpl<T> implements StorageRepository<T> {

    public FileRepository(Context context) {
        super(context);
    }

    @Override
    public T loadData(T storage) {
        Context context = getApplicationContext();
        String filename = storage.getFileName();

        InputStream fis = getInputStream(context, filename);

        return (fis == null) ? storage : (T) IOUtil.readObjectFromMemory(fis);
    }

    private InputStream getInputStream(Context context, String filename) {
        InputStream fis = null;
        try {
            String[] files = context.fileList();
            for (String file : files) {
                if (!file.equalsIgnoreCase(filename)) continue;
                fis = context.openFileInput(filename);
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fis;
    }
}
