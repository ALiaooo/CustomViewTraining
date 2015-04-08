package com.aliao.cvtraining.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 丽双 on 2015/4/8.
 */
public class DrawColor extends View {

    public DrawColor(Context context) {
        this(context, null);
    }

    public DrawColor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawColor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.RED);//直接将View显示区域用某个颜色填充满
    }
}
