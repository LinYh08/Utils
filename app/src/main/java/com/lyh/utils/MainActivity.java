package com.lyh.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.lyh.utils.base.BaseActivity;
import com.lyh.utils.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.btn_permission)
    Button btnPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEventAndData(Bundle saveInstanceState) {
        RxPermissions rxPermissions = new RxPermissions(this);
        RxView.clicks(btnPermission).compose(rxPermissions.ensure(Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            ToastUtil.showMessage("权限通过");
                        } else {
                            ToastUtil.showMessage("权限未通过");
                        }
                    }
                });
        RxView.clicks(tvContent).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Exception {
                ToastUtil.showMessage("防抖动");
                Log.i(TAG, "accept: ");
            }
        });
        RxTextView.textChanges(etName).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                Log.i(TAG, "accept: etName");
            }
        });
    }
}
