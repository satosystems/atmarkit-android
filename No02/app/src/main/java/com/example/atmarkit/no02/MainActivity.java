package com.example.atmarkit.no02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "@IT";
    public static final String ACTION_LOG = MainActivity.class.getPackage().getName() + ".ACTION_LOG";
    public static final String KEY_LOG = "KEY_LOG";

    private static class StringListAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        private final List<String> mList = new ArrayList<>();

        private StringListAdapter(Context context) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = (TextView) mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                textView = (TextView) convertView;
            }
            textView.setText(mList.get(position));
            return textView;
        }

        public void addItem(String item) {
            mList.add(0, mList.size() + ": " + item);
            notifyDataSetChanged();
        }
    };

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String log = intent.getStringExtra(KEY_LOG);
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainActivity.this.addLog(log);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new StringListAdapter(getBaseContext()));
        registerReceiver(mReceiver, new IntentFilter(ACTION_LOG));
        addLog("Activity#onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        addLog("Activity#onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        addLog("Activity#onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        addLog("Activity#onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        addLog("Activity#onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        addLog("Activity#onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addLog("Activity#onDestroy");
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        addLog("Activity#onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        addLog("Activity#onDetachedFromWindow");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        addLog("Activity#onAttachFragment");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        addLog("Activity#onResumeFragments");
    }

    public void onClickAddFragment(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        int count = layout.getChildCount();
        MainFragment fragment = MainFragment.newInstance(count);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.linearLayout, fragment);
        transaction.addToBackStack(String.valueOf(count));
        transaction.commit();
    }

    public void onClickRemoveFragment(View view) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    public void addLog(String log) {
        ListView listView = (ListView) findViewById(R.id.listView);
        if (listView != null) {
            StringListAdapter adapter = (StringListAdapter) listView.getAdapter();
            adapter.addItem(log);
        }
        Log.d(TAG, log);
    }
}
