package com.aliao.cvtraining.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 丽双 on 2015/4/8.
 * 画弧线
 */
public class DrawArc extends View {

    private Paint mPaint;

    public DrawArc(Context context) {
        this(context, null);
    }

    public DrawArc(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);//设置画笔为无锯齿
        mPaint.setStrokeWidth((float) 3.0);//线宽
        mPaint.setStyle(Paint.Style.STROKE);//空心
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制弧线区域
        RectF rectF = new RectF(200,0,300,200);
        canvas.drawArc(rectF, //弧线所使用的矩形区域大小
                0, //圆弧起始角度
                270, //扫过的角度，顺时针方向
                false,//是否使用中心，若为true，是弧线开始角度和结束角度都与中心点连接,在绘制圆弧时将圆心包括在内，通常用来绘制扇形
                mPaint);

        /*当 drawArcs(rect,startAngel,sweepAngel,useCenter,paint)中的useCenter为false时，
        弧线区域是用弧线开始角度和结束角度直接连接起来的，当useCenter为true时，是弧线开始角度和结束角度都与中心点连接，形成一个扇形。*/

    }
}
