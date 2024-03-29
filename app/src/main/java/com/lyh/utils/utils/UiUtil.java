package com.lyh.utils.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Random;


/**
 * 获取context以及ui需要的resource,全局维护一个handle,
 */
public class UiUtil {

    @SuppressLint("StaticFieldLeak")
    protected static Context mContext;
    private static UsageStatsManager sUsageStatsManager;

    public interface handlerListener {
        void refreshUI();
    }

    private static handlerListener mhandlerListener;

    public static handlerListener getMhandlerListener() {
        return mhandlerListener;
    }

    public static void setMhandlerListener(handlerListener mhandler) {
        mhandlerListener = mhandler;
    }
    protected static HandlerThread workThread = new HandlerThread("workThread");
    protected static Handler workHandler;

    protected static Handler sHandler = new Handler();

    public static void init(Application application) {
        mContext = application;
        workThread.start();
        workHandler = new Handler(workThread.getLooper());
    }

    public static Handler getHandler() {
        return sHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取res目录下string.xml,支持占位符
     *
     * @param resId
     * @return
     */
    public static String getString(int resId, Object... args) {
        return mContext.getResources()
                .getString(resId, args);
    }

    /**
     * 获取res目录下string.xml
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return mContext.getResources()
                .getString(resId);
    }

    public static Drawable getDrawable(int resId) {
        return mContext.getResources()
                .getDrawable(resId);
    }

    /**
     * 获取string.xml下的string-array
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return mContext.getResources()
                .getStringArray(resId);
    }

    public static String getRandomString(int resId) {
        String[] arrays = getStringArray(resId);
        if (arrays == null || arrays.length <= 0) {
            return "";
        } else {
            return arrays[new Random().nextInt(arrays.length)];
        }
    }
    public static String getRandomString(String... strings) {
        if (strings == null || strings.length <= 0) {
            return "";
        } else {
            return strings[new Random().nextInt(strings.length)];
        }
    }
    /**
     * 在工作线程中执行任务
     * @param task
     */
    public static void postOnWork(WorkRunnable task){
        workHandler.post(task);
    }
    public static void postOnWork(WorkRunnable task, long delay){
        workHandler.postDelayed(task, delay);
    }
    public static void postOnWork(Runnable task, long delay){
        workHandler.postDelayed(task, delay);
    }
    public static void postOnWork(Runnable task){
        workHandler.post(task);
    }
    public static void removeFromWork(Runnable task){
        workHandler.removeCallbacks(task);
    }
    /**
     * 接受一个任务，并且在主线程中执行它
     */
    public static void postOnMain(Runnable task) {
        sHandler.post(task);
    }

    public static void postOnMain(Runnable task, long delay) {
        sHandler.postDelayed(task, delay);
    }

    public static void removeFromMain(Runnable task) {
        sHandler.removeCallbacks(task);
    }

    /**
     * 获得栈顶的APP包名
     *
     * @param
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String getTopActivity() {
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1)
                .get(0).topActivity.getClassName();
        return runningActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static boolean isActivityRunning(Context mContext, String activityClassName) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(
                Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
