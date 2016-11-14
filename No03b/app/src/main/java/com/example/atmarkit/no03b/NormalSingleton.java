package com.example.atmarkit.no03b;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NormalSingleton {
    private static final NormalSingleton sInstance = new NormalSingleton();
    private final long mCreateTimestamp;

    private NormalSingleton() {
        mCreateTimestamp = System.currentTimeMillis();
    }

    public static NormalSingleton getInstance() {
        return sInstance;
    }

    @Override
    public String toString() {
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(mCreateTimestamp));
        return getClass().getSimpleName() + ": " + time;
    }
}
