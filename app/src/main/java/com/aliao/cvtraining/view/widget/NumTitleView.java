package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.ViewLogUtil;

/**
 * Created by 丽双 on 2015/8/27.
 */
public class NumTitleView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;



    public NumTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else {

        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {

        }
        mWidth = width;
        mHeight = height;

        setMeasuredDimension(width,height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        ViewLogUtil.onLayout(left, top, right, bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        ViewLogUtil.coordinate(getLeft(), getTop(), getRight(), getBottom());
        int left = 0;
        int top = 0;
        int right = left + mWidth;
        int bottom = top + mHeight;
        //要获取到控件的上下左右的坐标点，确定他的位置和大小，然后再来支笔才能开始画
        canvas.drawRect( left, top, right, bottom, mPaint);
//        canvas.drawColor(Color.BLUE);
//        canvas.clipRect(20, 20, 200, 200);
//        canvas.drawColor(Color.RED);
    }


}
