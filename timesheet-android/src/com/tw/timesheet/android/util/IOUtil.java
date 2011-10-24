package com.tw.timesheet.android.util;

import java.io.*;

public class IOUtil {

    public static String getStringFromStream(InputStream stream) {
        if (stream == null) return "";
        StringBuffer buffer = new StringBuffer();
        try {
            byte[] b = new byte[4096];
            for (int n; (n = stream.read(b)) != -1; ) {
                buffer.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeInputStream(stream);
        }
        return buffer.toString();
    }

    public static boolean writeObjectToDisk(OutputStream os, Serializable object) {
        boolean result = false;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(object);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeOutputStream(oos);
            closeOutputStream(os);
        }
        return result;
    }

    public static Object readObjectFromMemory(InputStream fis) {
        Object object = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeInputStream(ois);
            closeInputStream(fis);
        }
        return object;
    }

    private static void closeOutputStream(OutputStream fos) {
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void closeInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
