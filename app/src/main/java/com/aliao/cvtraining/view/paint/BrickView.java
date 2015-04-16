package com.aliao.cvtraining.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.aliao.cvtraining.R;

/**
 * Created by 丽双 on 2015/4/16.
 */
public class BrickView extends View {

    private Paint mFillPaint, mStrokePaint;
    private float posX, posY;

    public BrickView(Context context) {
        super(context);
    }

    public BrickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        /*
         * 实例化描边画笔并设置参数
         */
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrokePaint.setColor(0xFF000000);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(5);

        //实例化填充画笔
        mFillPaint = new Paint();

        //获取资源文件
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.brick);
        /**
         * 设置BitmapShader
         */
        mFillPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 手指移动时获取触摸点坐标并刷新视图
         */
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            posX = event.getX();
            posY = event.getY();

            invalidate();
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔背景色
        canvas.drawColor(Color.DKGRAY);

        /**
         * 绘制圆和描边
         */
        canvas.drawCircle(posX, posY, 100, mFillPaint);
        canvas.drawCircle(posX, posY, 100, mStrokePaint);

    }
}
