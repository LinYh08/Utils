package com.lyh.utils;

import android.app.Application;

import com.lyh.utils.base.BaseApp;
import com.lyh.utils.log.LogWriter;

/**
 * 包名：com.lyh.utils
 * 创建者：LinYh
 * 文件名：App
 * 描述：
 * <p>
 * 时间：2019/12/6
 */

public class App extends Application {
    private static App instance = null;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        BaseApp.initAppContext(this);
        LogWriter.init();
    }
}
