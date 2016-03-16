package com.example.atmarkit.no02;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainApplication extends Application implements Application.ActivityLifecycleCallbacks {
    public static final String TAG = "@IT";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Application#onCreate");
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "Application#onTerminate");
        unregisterActivityLifecycleCallbacks(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "Application#onConfigurationChanged");
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "ActivityLifecycleCallbacks#onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "ActivityLifecycleCallbacks#onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "ActivityLifecycleCallbacks#onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, "ActivityLifecycleCallbacks#onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG, "ActivityLifecycleCallbacks#onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.d(TAG, "ActivityLifecycleCallbacks#onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, "ActivityLifecycleCallbacks#onActivityDestroyed");
    }
}
