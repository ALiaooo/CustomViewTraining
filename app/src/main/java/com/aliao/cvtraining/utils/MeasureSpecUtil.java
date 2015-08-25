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
    public static void printMeasureSpec(String tag, int widthMeasureSpec, int heightMeasureSpec){

        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        L.d("> onMeasure: "+tag+"  [w: "+widthSize+"  "+getMode(widthMode)+",       h: "+heightSize+"  "+getMode(heightMode)+"]");
    }

    private static String getMode(int mode){
        if (mode == View.MeasureSpec.AT_MOST){
            return "at_most";
        }else if (mode == View.MeasureSpec.EXACTLY){
            return "exactly";
        }else if (mode == View.MeasureSpec.UNSPECIFIED){
            return "unspecified";
        }
        return null;
    }
}
