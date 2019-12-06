package com.lyh.utils.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 包名：com.lyh.utils.base
 * 创建者：LinYh
 * 文件名：BaseSimpleFragment
 * 描述：
 * <p>
 * 时间：2019/12/6
 */

public abstract class BaseSimpleFragment extends Fragment {
    protected Activity mActivity;
    protected Context mContext;
    private View mView;
    private Unbinder mUnbinder;
    private boolean isInited = false;

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        mActivity= (Activity) context;
        mContext=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(), null);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        if (!isHidden()){
            isInited=true;
            initDataAndEvent();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden&&!isInited){
            isInited=true;
            initDataAndEvent();
        }
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
    protected abstract int getLayout();
    protected abstract void initDataAndEvent();
}
