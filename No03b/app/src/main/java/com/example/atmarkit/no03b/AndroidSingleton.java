package com.example.atmarkit.no03b;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AndroidSingleton {
    private final long mCreateTimestamp;

    private AndroidSingleton() {
        mCreateTimestamp = System.currentTimeMillis();
    }

    public static AndroidSingleton getInstance() {
        return MainApplication.getInstance().getAndroidSingleton();
    }

    @Override
    public String toString() {
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(mCreateTimestamp));
        return getClass().getSimpleName() + ": " + time;
    }
}
