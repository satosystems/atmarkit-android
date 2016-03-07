package com.example.atmarkit.no02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.atmarkit.no02.MainApplication.TAG;

public class MainFragment extends Fragment {
    private static final String PARAM_NUMBER = "number";

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
        Log.d(TAG, "Fragment#onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment#onDestroy");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Fragment#onCreateView");
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        int number = getArguments().getInt(PARAM_NUMBER);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(String.valueOf(number));
        return view;
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
        Log.d(TAG, "Fragment#onActivityCreated");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "Fragment#onViewStateRestored");
    }
}
