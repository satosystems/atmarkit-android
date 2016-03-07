package com.example.atmarkit.no02;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.example.atmarkit.no02.MainApplication.TAG;

public class MainActivity extends AppCompatActivity {
    private static final String PREFIX = MainActivity.class.getPackage().getName() + ":";
    private static final String KEY_NUMBER = PREFIX + "NUMBER";
    private static final String KEY_SCROLL_Y = PREFIX + "SCROLL_Y";
    private static final String KEY_TEXT_VIEWS = PREFIX + "TEXT_VIEWS";
    private Bundle mState = new Bundle();
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Activity#onCreate: " + savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                mState.putInt(KEY_SCROLL_Y, mScrollView.getScrollY());
            }
        });
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
        ArrayList<String> list = new ArrayList<>();
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = layout.getChildAt(i);
            if (child instanceof TextView) {
                TextView textView = (TextView) child;
                list.add(textView.getText().toString());
            }
        }
        mState.putStringArrayList(KEY_TEXT_VIEWS, list);
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

        int scrollY = mState.getInt(KEY_SCROLL_Y);
        mScrollView.scrollTo(0, scrollY);
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
        Log.d(TAG, "Activity#onRestoreInstanceState: " + savedInstanceState);

        if (savedInstanceState != null) {
            Bundle bundle = new Bundle(savedInstanceState);
            for (String key : savedInstanceState.keySet()) {
                if (!key.startsWith(PREFIX)) {
                    bundle.remove(key);
                }
            }
            mState.putAll(bundle);
        }

        ArrayList<String> list = mState.getStringArrayList(KEY_TEXT_VIEWS);
        if (list != null) {
            for (String text : list) {
                addTextView(String.valueOf(text));
            }
            mState.remove(KEY_TEXT_VIEWS);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "Activity#onPostCreate: " + savedInstanceState);
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
        String number = String.format("%1$03d", getContentNumber());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss.SSS", Locale.getDefault());
        addTextView(number + " " + sdf.format(new Date()));
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

    private void addTextView(String text) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        TextView textView = new TextView(getBaseContext());
        textView.setTextSize(24);
        textView.setText(String.valueOf(text));
        layout.addView(textView);
    }

    private int getContentNumber() {
        int number = mState.getInt(KEY_NUMBER);
        number++;
        mState.putInt(KEY_NUMBER, number);
        return number;
    }
}
