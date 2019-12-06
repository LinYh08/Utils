package com.lyh.utils.log;

import android.util.Log;

import com.lyh.utils.base.Constants;
import com.lyh.utils.utils.FileUtils;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * loggerWriter
 * Created by Administrator on 2018/8/11.
 */

public class LogWriter {

    private static final int MAX_SIZE = 50;

    public static void init() {
        //初始化Logger
        FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
                .build();
        com.orhanobut.logger.Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));

        File logFileDir = new File(Constants.ROBOT_LOG_DIR);
        if (logFileDir.exists()) {
            List<File> files = Arrays.asList(logFileDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".csv");
                }
            }));
            Collections.sort(files, new Comparator<File>() {
                @Override
                public int compare(File file1, File file2) {
                    String name1 = file1.getName();
                    int index1 = name1.indexOf("_");
                    String date1 = name1.substring(index1, index1 + 8);

                    String name2 = file2.getName();
                    int index2 = name2.indexOf("_");
                    String date2 = name2.substring(index2, index2 + 8);
                    return date1.compareTo(date2);
                }
            });
            int size = files.size();
            if (size > MAX_SIZE) {
                for (int i = MAX_SIZE; i < size; i++) {
                    FileUtils.deleteFile(files.get(i));
                }
            }
        }
    }

    public static void writeExceptionLog(String log) {
        com.orhanobut.logger.Logger.e(log);
        Log.e(Constants.TAG, log);
    }

    public static void writeExceptionLog(String log, int second) {
        long currentSecond = System.currentTimeMillis() / 1000;
        if (currentSecond % second == 0) {
            writeExceptionLog(log);
        }
    }

    public static void writeOperateLog(String log) {
        com.orhanobut.logger.Logger.d(log);
        Log.d(Constants.TAG, log);
    }

    public static void writeOperateLog(String log, int second) {
        long currentSecond = System.currentTimeMillis() / 1000;
        if (currentSecond % second == 0) {
            writeOperateLog(log);
        }
    }

    public static void writeJSON(String json) {
        com.orhanobut.logger.Logger.json(json);
        Log.d(Constants.TAG, json);
    }

    /**
     * 打印 json log
     *
     * @param json   json文本
     * @param second 时间间隔，秒
     */
    public static void writeJSON(String json, int second) {
        long currentSecond = System.currentTimeMillis() / 1000;
        if (currentSecond % second == 0) {
            writeJSON(json);
        }
    }
}
