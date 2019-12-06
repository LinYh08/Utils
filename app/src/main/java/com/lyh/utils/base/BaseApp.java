package com.lyh.utils.base;

import android.content.Context;

public class BaseApp {

    public static Context mAppContext;

    public static Context getAppContext() {
        return mAppContext;
    }

    public static void initAppContext(Context context) {
        mAppContext = context;
    }

    public static BaseApp instance;
    public BaseApp(){}

    public static BaseApp get(){
        if (instance==null){
            synchronized (BaseApp.class){
                if (instance==null){
                    instance=new BaseApp();
                }
            }
        }
        return instance;
    }

    /**
     * 是否可以打断
     */
    private boolean breakIn = false;

    public boolean isBreakIn() {
        return breakIn;
    }

    public void setBreakIn(boolean breakIn) {
        this.breakIn = breakIn;
    }
}
