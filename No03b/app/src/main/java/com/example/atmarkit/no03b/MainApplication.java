package com.example.atmarkit.no03b;

import android.app.Application;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainApplication extends Application {
    private static MainApplication sInstance;
    private AndroidSingleton mAndroidSingleton;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        try {
            Constructor<AndroidSingleton> constructor = AndroidSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            mAndroidSingleton = constructor.newInstance();
        } catch (NoSuchMethodException |
                IllegalAccessException |
                InstantiationException |
                InvocationTargetException e) {
            // never happen
        }
    }

    public static MainApplication getInstance() {
        return sInstance;
    }

    public AndroidSingleton getAndroidSingleton() {
        return mAndroidSingleton;
    }
}
