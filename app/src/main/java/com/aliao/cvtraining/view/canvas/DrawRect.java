package com.aliao.cvtraining.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 丽双 on 2015/4/8.
 *
 */
public class DrawRect extends View {

    private Paint mPaint;

    public DrawRect(Context context) {
        this(context, null);
    }

    public DrawRect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth((float) 4.0);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        RectF rectF = new RectF(10, 10, 110, 110);
        canvas.drawRect(rectF, mPaint);
    }
}
