package com.aliao.cvtraining.view.canvas;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 丽双 on 2015/4/8.
 */
public abstract class DrawShape extends View {
    public Paint mPaint;
    public DrawShape(Context context) {
        this(context, null);
    }

    public DrawShape(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawShape(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        setPaintColor();
    }

    abstract void setPaintColor();

}
