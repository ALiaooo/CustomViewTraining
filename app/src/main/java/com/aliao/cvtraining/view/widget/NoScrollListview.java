package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.aliao.cvtraining.utils.L;

/**
 * Created by sf on 2015/2/10.
 * desc:
 */
public class NoScrollListview extends ListView {

    public NoScrollListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //ÉèÖÃ²»¹ö¶¯
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);//UNSPECIFIED
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        L.d("heightMode = " + heightMode + ", heightSize = " + heightSize + ", widthMode = " + widthMode + ", widthSize = " + widthSize);
        if (heightMode == MeasureSpec.AT_MOST){
            L.d("mode1  = AT_MOST");
        }else if (heightMode == MeasureSpec.EXACTLY){
            L.d("mode1  = EXACTLY");
        }else if (heightMode == MeasureSpec.UNSPECIFIED){
            L.d("mode1  = UNSPECIFIED");
        }


        //http://zhidao.baidu.com/link?url=rjqa4ZIewMsh64Pw35bNCtPRpWNIzlUrQP6GEsa9SLcM6nfqbtuJIUlIWxdlRBjvzwAhQ5RM1OeGst7abEIjtObpsgAIHrO2ASL8h9p2y4m
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        int expandHeightMode = MeasureSpec.getMode(expandSpec);//AT_MOST
        int expandHeightSize = MeasureSpec.getSize(expandSpec);

        L.d("expandSpecHeight = "+expandHeightMode+", expandHeightSize = "+expandHeightSize);
        if (expandHeightMode == MeasureSpec.AT_MOST){
            L.d("mode2  = AT_MOST");
        }else if (expandHeightMode == MeasureSpec.EXACTLY){
            L.d("mode2  = EXACTLY");
        }else if (expandHeightMode == MeasureSpec.UNSPECIFIED){
            L.d("mode2  = UNSPECIFIED");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

}
