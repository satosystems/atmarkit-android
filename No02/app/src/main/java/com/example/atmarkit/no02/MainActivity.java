package com.example.atmarkit.no02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.atmarkit.no02.MainApplication.TAG;

public class MainActivity extends AppCompatActivity {
    private static final String PREFIX = MainActivity.class.getPackage().getName() + ":";
    private static final String KEY_NUMBER = PREFIX + "NUMBER";
    private Bundle mState = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Activity#onCreate");
        setContentView(R.layout.activity_main);
        restoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Activity#onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Activity#onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Activity#onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Activity#onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Activity#onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Activity#onDestroy");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "Activity#onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "Activity#onDetachedFromWindow");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.d(TAG, "Activity#onAttachFragment");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Log.d(TAG, "Activity#onResumeFragments");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Activity#onSaveInstanceState");
        outState.putAll(mState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "Activity#onRestoreInstanceState");
        restoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "Activity#onPostCreate");
    }

    public void onClickAddFragment(View view) {
        int number = getContentNumber();
        MainFragment fragment = MainFragment.newInstance(number);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.linearLayout, fragment);
        transaction.addToBackStack(String.valueOf(number));
        transaction.commit();
    }

    public void onClickRemoveFragment(View view) {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
    }

    public void onClickAddView(View view) {
        int number = getContentNumber();
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

        TextView textView = new TextView(getBaseContext());
        textView.setTextSize(24);
        textView.setText(String.valueOf(number));
        layout.addView(textView);
    }

    public void onClickRemoveView(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        int count = layout.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            View child = layout.getChildAt(i);
            if (child instanceof TextView) {
                layout.removeView(child);
                break;
            }
        }
    }

    private int getContentNumber() {
        int number = mState.getInt(KEY_NUMBER);
        number++;
        mState.putInt(KEY_NUMBER, number);
        return number;
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.size() != 0) {
                mState.putAll(extras);
            }
        }
        if (savedInstanceState != null) {
            Bundle bundle = new Bundle(savedInstanceState);
            for (String key : savedInstanceState.keySet()) {
                if (!key.startsWith(PREFIX)) {
                    bundle.remove(key);
                }
            }
            mState.putAll(bundle);
        }
        dumpBundle(mState);
    }

    private void dumpBundle(Bundle bundle) {
        for (String key : mState.keySet()) {
            Log.d(TAG, "　" + key + "=" + mState.get(key));
        }
    }
}
