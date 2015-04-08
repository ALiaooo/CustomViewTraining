package com.aliao.cvtraining.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 丽双 on 2015/4/8.
 *
 */
public class DrawCircle extends View{

    private Paint mPaint;
    public DrawCircle(Context context) {
        this(context, null);
    }

    public DrawCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(100, 100, 100, mPaint);
    }
}
