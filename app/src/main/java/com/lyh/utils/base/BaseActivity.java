package com.lyh.utils.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lyh.utils.utils.AppStackManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity {
    private CompositeDisposable mCompositeDisposable=null;
    private Unbinder unBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unBinder = ButterKnife.bind(this);
        initEventAndData(savedInstanceState);
        mCompositeDisposable=new CompositeDisposable();
        AppStackManager.getInstance().addActivity(this);
    }

    /**
     * 添加订阅
     * @param disposable
     */
    protected void addDisposable(Disposable disposable){
        if (mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消订阅
     */
    protected void clearDisposable(){
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();

        }
    }
    @Override
    protected void onDestroy() {
        unBinder.unbind();
        AppStackManager.getInstance().removeActivity(this);
        clearDisposable();
        super.onDestroy();
    }

    protected abstract int getLayout();

    /**
     * 加载数据
     */
    protected abstract void initEventAndData(Bundle saveInstanceState);
}
