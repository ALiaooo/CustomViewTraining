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

    public static void touchLog(String view, String event, String action){
        String whichView =  flushLeft('.',12,view);//String.format("%1$20s", view);
        String whichEvent = String.format("%-20s", event);
        L.d(whichView+" > "+whichEvent+" -         "+action);
    }


    // c 要填充的字符
    // l 填充后字符串的总长度
    // string 要格式化的字符串
    public static String flushLeft(char c, long l, String string)
    {
        String str;
        String cs = "";
        if (string.length() > l)
            str = string;
        else
            for (int i = 0; i < l - string.length(); i++)
                cs = cs + c;
        str = cs + string;
        return str;
    }


}
