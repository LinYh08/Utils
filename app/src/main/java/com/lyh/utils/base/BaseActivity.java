package com.lyh.utils.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lyh.utils.utils.AppStackManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unBinder = ButterKnife.bind(this);
        initEventAndData(savedInstanceState);
        AppStackManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        unBinder.unbind();
        AppStackManager.getInstance().removeActivity(this);
        super.onDestroy();
    }

    protected abstract int getLayout();

    /**
     * 加载数据
     */
    protected abstract void initEventAndData(Bundle saveInstanceState);
}
