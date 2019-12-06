package com.lyh.utils.base;

import android.os.Environment;

/**
 * 包名：com.lyh.utils.base
 * 创建者：LinYh
 * 文件名：Constants
 * 描述：
 * <p>
 * 时间：2019/12/6
 */

public class Constants {
    public final static String TAG = "lotteryTag";

    /**
     * 机器人日志记录文件
     */
    public static final String ROBOT_LOG_DIR = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/LotteryLogger";
}
