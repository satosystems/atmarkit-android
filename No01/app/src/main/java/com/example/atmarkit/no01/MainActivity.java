package com.example.atmarkit.no01;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "@IT";
    private static final int REQUEST_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SETTINGS) {
            startService();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void startService() {
        Intent intent = null;
        if (!canGetUsageStats()) {
            intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        } else if (!canDrawOverlays()) {
            Uri uri = Uri.parse("package:" + getPackageName());
            intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri);
        }
        if (intent != null) {
            startActivityForResult(intent, REQUEST_SETTINGS);
            Toast.makeText(getApplicationContext(), "Please turn ON", Toast.LENGTH_SHORT).show();
        } else {
            startService(new Intent(MainActivity.this, MainService.class));
        }
    }

    public boolean canGetUsageStats() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return true;
        }
        AppOpsManager aom = (AppOpsManager) getSystemService(APP_OPS_SERVICE);
        int uid = android.os.Process.myUid();
        int mode = aom.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, uid, getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    private boolean canDrawOverlays() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        return Settings.canDrawOverlays(getApplicationContext());
    }
}
