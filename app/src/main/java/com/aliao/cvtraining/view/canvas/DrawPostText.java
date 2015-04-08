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
public class DrawPostText extends View {

    private Paint mPaint;

    public DrawPostText(Context context) {
        this(context, null);
    }

    public DrawPostText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPostText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth((float) 4.0);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize((float) 30.0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("Android", 50, 100, mPaint);
    }
}
