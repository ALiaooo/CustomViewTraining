package com.aliao.cvtraining.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/4/10
 */
public class CustomCircularRingView extends View implements Runnable{

    private Paint mPaint;
    private int radiu = 50;

    public CustomCircularRingView(Context context) {
        super(context);
        L.d("C1");
    }

    public CustomCircularRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        L.d("C2");

        /**
         * 初始化画笔
         */
        initPaint();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
        /**
         * 实例化画笔并打开抗锯齿
         */
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 设置画笔样式为描边
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE 描边
         * 2.Paint.Style.FILL_AND_STROKE 描边并填充
         * 3.Paint.Style.FILL 填充
         */
        mPaint.setStyle(Paint.Style.STROKE);

        /**
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrockWidth(0)的时候描边宽度并不为0而只是占了一个像素
         */
        mPaint.setStrokeWidth(10);

        //设置画笔颜色为浅灰色
        mPaint.setColor(Color.LTGRAY);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        L.d("onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        L.d("onDraw");
        /**
         * 绘制圆环
         *
         * 注：在Android中设置数字类型的参数时如果没有特别的说明，参数的单位一般都是像素px
         */
        canvas.drawCircle(200, 200, radiu, mPaint);
    }

    public void setRadiu(int radiu) {
        this.radiu = radiu;

        //重绘
        invalidate();

        L.d("setRadius");
    }

    @Override
    public void run() {

        /**
         * 确保线程不断执行不断刷新界面
         */
        while (true){

            if(radiu <= 200){
                radiu += 10;

                //刷新view
                postInvalidate();
            }else {
                radiu = 0;
            }

            try {
                Thread.sleep(60);//执行一次暂停40毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

