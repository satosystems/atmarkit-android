package com.example.atmarkit.no01;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.example.atmarkit.no01.MainActivity.TAG;

public class MainService extends Service implements View.OnTouchListener {
    private static final int TAP_COUNT = 3;
    private View mView;
    private long[] mTimestamp = new long[TAP_COUNT];

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                0,
                0,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.OPAQUE);

        mView = new View(this);
        mView.setOnTouchListener(this);

        wm.addView(mView, params);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wm.removeView(mView);
        mView = null;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(TAG, "event: " + event);

        if (event.getAction() != MotionEvent.ACTION_OUTSIDE) {
            return false;
        }

        String packageName = getTopActivityPackageName();
        boolean matched = packageName.equals("com.android.chrome") || packageName.equals("com.android.browser");

        Log.d(TAG, "matched: " + matched);

        if (!matched) {
            return false;
        }

        mTimestamp[0] = event.getEventTime();
        Arrays.sort(mTimestamp);

        long diff = 0;
        for (int i = 0; i < mTimestamp.length - 1; i++) {
            diff += mTimestamp[i + 1] - mTimestamp[i];
        }
        Log.d(TAG, "diff: " + diff);
        if (diff < 500) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Launch from " + packageName, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private String getTopActivityPackageName() {
        String packageName = "";
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
            packageName = list.get(0).processName;
        } else {
            UsageStatsManager usm = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
            long endTime = System.currentTimeMillis();
            long beginTime = endTime - 7 * 24 * 60 * 60 * 1000;
            List<UsageStats> list = usm.queryUsageStats(UsageStatsManager.INTERVAL_BEST, beginTime, endTime);
            if (list != null && list.size() > 0) {
                SortedMap<Long, UsageStats> map = new TreeMap<>();
                for (UsageStats usageStats : list) {
                    map.put(usageStats.getLastTimeUsed(), usageStats);
                    Log.d(TAG, "package: " + usageStats.getPackageName());
                }
                Log.d(TAG, "size: " + map.size());
                if (!map.isEmpty()) {
                    packageName = map.get(map.lastKey()).getPackageName();
                }
            }
        }
        Log.d(TAG, "Current packageName: " + packageName);

        return packageName;
    }
}
