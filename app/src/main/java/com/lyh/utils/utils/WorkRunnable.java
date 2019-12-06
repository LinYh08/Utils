package com.lyh.utils.utils;


import android.util.Log;

public abstract class WorkRunnable implements Runnable {
    private static final String TAG = "WorkRunnable";
    private String workName;
    public WorkRunnable(String name){
        workName = name;
    }
    public abstract void doRun();
    @Override
    public void run() {
        try {
            Log.d(TAG, workName + " doRun() start at :" + System.currentTimeMillis());
            doRun();
            Log.d(TAG, workName + " doRun() end at :" + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
