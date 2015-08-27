package com.aliao.cvtraining.utils;

import android.util.Log;

/**
 * Created by 丽双 on 2015/8/27.
 */
public class ViewLogUtil {

    public static void onLayout(int left, int top, int right, int bottom){
        L.d("> onLayout: "+"left = "+left+", top = "+top+", right = "+right+", bottom = "+bottom);
    }

    public static void coordinate(int left, int top, int right, int bottom){
        L.d("> 四个顶点: "+"left = "+left+", top = "+top+", right = "+right+", bottom = "+bottom);
    }
}
