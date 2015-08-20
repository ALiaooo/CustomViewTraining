package com.aliao.cvtraining.utils;

import android.view.View;

/**
 * Created by 丽双 on 2015/8/20.
 */
public class MeasureSpecUtil {

    public static void printMode(int mode, String tag){
        if (mode == View.MeasureSpec.AT_MOST){
            L.d(tag+" mode  = AT_MOST");
        }else if (mode == View.MeasureSpec.EXACTLY){
            L.d(tag+" mode  = EXACTLY");
        }else if (mode == View.MeasureSpec.UNSPECIFIED){
            L.d(tag+" mode  = UNSPECIFIED");
        }
    }
}
