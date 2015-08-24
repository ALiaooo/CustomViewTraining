package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.MeasureSpecUtil;

/**
 * Created by 丽双 on 2015/8/24.
 */
public class CustomFrameLayout extends FrameLayout {

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        L.d("---------------onMeasure FrameLayout----------------");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        L.d("heightMode = " + heightMode + ", heightSize = " + heightSize + ", widthMode = " + widthMode + ", widthSize = " + widthSize);
        MeasureSpecUtil.printMode(heightMode, "height");
        MeasureSpecUtil.printMode(widthMode, "width");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
