package com.lyh.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lyh.utils.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData(Bundle saveInstanceState) {

    }
}
