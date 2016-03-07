package com.example.atmarkit.no02;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class MainApplication extends Application {
    public static final String TAG = "@IT";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Application#onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "Application#onTerminate");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "Application#onTerminate");
    }
}
