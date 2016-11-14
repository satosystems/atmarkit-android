package com.example.atmarkit.no03b;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mMyPid;
    private String mSingletonValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mMyPid = savedInstanceState.getInt("myPid");
            mSingletonValue = savedInstanceState.getString("singletonValue");
        }
        int pid = android.os.Process.myPid();
        if (mMyPid == 0) {
            Toast.makeText(getApplicationContext(), "アクティビティが起動しました", Toast.LENGTH_LONG).show();
        } else if (mMyPid != pid) {
            Toast.makeText(getApplicationContext(), "プロセスが再起動しました", Toast.LENGTH_LONG).show();
        }
        mMyPid = pid;

        String singletonValue = NormalSingleton.getInstance().toString();
        if (mSingletonValue != null && !TextUtils.equals(mSingletonValue, singletonValue)) {
            Toast.makeText(getApplicationContext(), "シングルトンインスタンスが再生成されました", Toast.LENGTH_LONG).show();
        }
        mSingletonValue = singletonValue;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StringBuilder sb = new StringBuilder();
        sb.append(NormalSingleton.getInstance()).append("\n");
        sb.append(AndroidSingleton.getInstance()).append("\n");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(sb);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("myPid", mMyPid);
        outState.putString("singletonValue", mSingletonValue);
    }
}
