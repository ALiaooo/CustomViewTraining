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
public class DrawLine extends View {

    private Paint mPaint;

    public DrawLine(Context context) {
        this(context, null);
    }

    public DrawLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth((float) 4.0);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(50, 0, 250, 100, mPaint);
        //绘制多条直线
       /* mPaint.setColor(Color.BLACK);                    //设置画笔颜色
        float[] pts={50,50,400,50,
                400,50,400,600,
                400,600,50,600,
                60,600,50,50};                  //数据
        canvas.drawColor(Color.WHITE);                  //白色背景
        mPaint.setStrokeWidth((float) 5.0);              //线宽
        canvas.drawLines(pts, mPaint);                   //绘制多条直线  */

        //有选择的绘制
      /*  mPaint.setColor(Color.BLACK);                    //设置画笔颜色
        float[] pts={50,50,400,50,
                400,50,400,600,
                400,600,50,600,
                60,600,50,50};                  //数据
        canvas.drawColor(Color.WHITE);                  //白色背景
        mPaint.setStrokeWidth((float) 5.0);              //线宽
        canvas.drawLines(pts,0,16,mPaint);               //有选择地绘制直线*/
    }
}
