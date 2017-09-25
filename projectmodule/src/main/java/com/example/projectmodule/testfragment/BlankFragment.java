package com.example.projectmodule.testfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectmodule.R;
import com.example.projectmodule.utils.LogUtils;

import java.util.Random;

/**
 * Created by 吴城林 on 2017/9/2.
 */

public class BlankFragment extends Fragment {

    private TextView textView;
    private final static String ARGUMENT = "argument";
    private String params;

    public static BlankFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.blank_fragment_text
        );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (params == null) {
            params = String.valueOf(new Random().nextInt(10));
            LogUtils.e("params is NULL.........................."+getArguments());
        }
        textView.setText("params:" + params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("fragmentDestory" + getArguments().getString(ARGUMENT
        ));
    }
}
