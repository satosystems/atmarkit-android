package com.example.atmarkit.no02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        addLog("Fragment#onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        addLog("Fragment#onDestroy");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addLog("Fragment#onCreateView");
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        int number = getArguments().getInt(PARAM_NUMBER);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(String.valueOf(number));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addLog("Fragment#onDestroyView");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addLog("Fragment#onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addLog("Fragment#onDetach");
    }

    @Override
    public void onStart() {
        super.onStart();
        addLog("Fragment#onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        addLog("Fragment#onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        addLog("Fragment#onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        addLog("Fragment#onStop");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addLog("Fragment#onActivityCreated");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        addLog("Fragment#onViewStateRestored");
    }

    private void addLog(String log) {
        Intent intent = new Intent(MainActivity.ACTION_LOG);
        intent.putExtra(MainActivity.KEY_LOG, log);
        getContext().sendBroadcast(intent);
    }
}
