package com.example.atmarkit.no03a;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(@NonNull View view) {
        killBackgroundProcesses();
    }

    private void killBackgroundProcesses() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = getPackageManager();
        List<PackageInfo> list = pm.getInstalledPackages(0);

        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        long availMem = memoryInfo.availMem;
        for (PackageInfo packageInfo : list) {
            am.killBackgroundProcesses(packageInfo.packageName);
        }
        am.getMemoryInfo(memoryInfo);

        showResult(availMem, memoryInfo.availMem, list);
    }

    private void showResult(long before, long after, @NonNull List<PackageInfo> killedApps) {
        TextView textView = (TextView) findViewById(R.id.textView);
        String beforeMB = String.format(Locale.getDefault(), "%.1fMB", (float) before / 1024 / 1024);
        String afterMB = String.format(Locale.getDefault(), "%.1fMB", (float) after / 1024 / 1024);
        String boostedMB = String.format(Locale.getDefault(), "%.1fMB", (float) (after - before) / 1024 / 1024);

        StringBuilder sb = new StringBuilder();
        sb.append("# Memory\n");
        sb.append("- before: ").append(beforeMB).append("\n");
        sb.append("- after: ").append(afterMB).append("\n");
        sb.append("- boosted: ").append(boostedMB).append("\n");
        sb.append("\n");
        sb.append("# Killed Processes\n");
        for (PackageInfo packageInfo : killedApps) {
            sb.append("- ").append(packageInfo.packageName).append("\n");
        }

        textView.setText(new String(sb));
    }
}
