package com.example.atmarkit.no02;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.atmarkit.no02.MainApplication.TAG;

public class MainFragment extends Fragment {
    private static final String PREFIX = MainActivity.class.getPackage().getName() + ":";
    private static final String KEY_TEXT = PREFIX + "TEXT";
    private static final String PARAM_NUMBER = "number";
    private final Bundle mState = new Bundle();

    public static MainFragment newInstance(int number) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Fragment#onCreate: " + savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment#onDestroy");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Fragment#onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.d(TAG, "Fragment#onInflate: " + savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "Fragment#onViewCreated: " + savedInstanceState);

        if (savedInstanceState != null) {
            Bundle bundle = new Bundle(savedInstanceState);
            for (String key : savedInstanceState.keySet()) {
                if (!key.startsWith(PREFIX)) {
                    bundle.remove(key);
                }
            }
            mState.putAll(bundle);
        }

        TextView textView = (TextView) view.findViewById(R.id.textView);
        String text = mState.getString(KEY_TEXT);
        if (text == null) {
            String number = String.format("[%1$03d]", getArguments().getInt(PARAM_NUMBER));
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS", Locale.getDefault());
            text = number + " " + sdf.format(new Date()) + " Fragment";
            mState.putString(KEY_TEXT, text);
        }
        textView.setText(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "Fragment#onDestroyView");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "Fragment#onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "Fragment#onDetach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Fragment#onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Fragment#onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Fragment#onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment#onStop");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Fragment#onActivityCreated: " + savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "Fragment#onViewStateRestored: " + savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Fragment#onSaveInstanceState");
        outState.putAll(mState);
    }
}
