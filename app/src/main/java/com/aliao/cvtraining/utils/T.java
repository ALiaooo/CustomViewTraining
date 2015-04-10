package com.aliao.cvtraining.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 丽双 on 2015/3/20.
 */
public class T {

    public static void showToastShort(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
