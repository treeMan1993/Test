package com.example.test.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.test.BuildConfig;


public class LogUtil {

    public static void d(String TAG, String text) {
        if (!BuildConfig.LOG_DISPLAY) return;
        if (!TextUtils.isEmpty(text)) {
            Log.d(TAG, text);
        } else {
            Log.d(TAG, TAG+":传入的参数数据为空");
        }
    }

    public static void i(String TAG, String text) {
        if (!BuildConfig.LOG_DISPLAY) return;
        if (!TextUtils.isEmpty(text)) {
            Log.i(TAG, text);
        } else {
            Log.i(TAG, TAG+":传入的参数数据为空");
        }
    }

    public static void w(String TAG, String text) {
        if (!BuildConfig.LOG_DISPLAY) return;
        if (!TextUtils.isEmpty(text)) {
            Log.w(TAG, text);
        } else {
            Log.w(TAG, TAG+":传入的参数数据为空");
        }
    }

    public static void e(String TAG, String text) {
        if (!BuildConfig.LOG_DISPLAY) return;
        if (!TextUtils.isEmpty(text)) {
            Log.e(TAG, text);
        } else {
            Log.e(TAG, TAG+":传入的参数数据为空");
        }
    }

}
