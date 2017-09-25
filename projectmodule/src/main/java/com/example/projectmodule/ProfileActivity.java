package com.example.projectmodule;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projectmodule.base.BaseActivity;
import com.example.projectmodule.utils.MyApplication;

public class ProfileActivity extends BaseActivity {

    private TextView textView;

    @Override
    protected void setUpContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile,R.string.none_title,MODE_BACK);
    }

    @Override
    protected void setUpView() {
        textView= (TextView) findViewById(R.id.profile_text);
        textView.setText(MyApplication.profile.toString());
    }

}
