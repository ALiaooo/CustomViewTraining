package com.aliao.cvtraining.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/13.
 */
public class DisInView extends View {
    private Paint mPaint;// 画笔
    private Bitmap bitmapDis;// 位图
    private Bitmap bitmapSrc;// 位图

    private int x, y;// 位图绘制时左上角的起点坐标
    private int screenW, screenH;// 屏幕尺寸

    private PorterDuffXfermode porterDuffXfermode;// 图形混合模式

    public DisInView(Context context) {
        this(context, null);
    }

    public DisInView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化混合模式
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        // 初始化画笔
        initPaint();

        // 初始化资源
        initRes(context);
    }

    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void initRes(Context context) {
        // 获取位图
        bitmapDis = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a3);//抠出美女轮廓时只需要a3_mask
//        bitmapSrc = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a3_mask);

        // 获取包含屏幕尺寸的数组
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        // 获取屏幕尺寸
        screenW = screenSize[0];
        screenH = screenSize[1];

        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = screenW / 2 - bitmapDis.getWidth() / 2;
        y = screenH / 2 - bitmapDis.getHeight() / 2;
//        x = screenW / 2 - bitmapSrc.getWidth() / 2;
//        y = screenH / 2 - bitmapSrc.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 1. 抠出美女
         */
      /*  canvas.drawColor(Color.WHITE);
        *//**
         * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
         *//*
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        //先绘制dis目标图
        canvas.drawBitmap(bitmapDis, x, y, mPaint);

        //设置混合模式
        mPaint.setXfermode(porterDuffXfermode);

        //再绘制src源图
        canvas.drawBitmap(bitmapSrc, x, y, mPaint);

        //还原混合模式
        mPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(sc);*/


        /**
         * 2. 从色块中抠出美女轮廓
         */
      /*  canvas.drawColor(Color.WHITE);
        *//**
        * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
        *//*
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        //先绘制一层颜色
        canvas.drawColor(0xFF8f66DA);

        //设置混合模式
        mPaint.setXfermode(porterDuffXfermode);//DST_OUT

        //再绘制src源图
        canvas.drawBitmap(bitmapSrc, x, y, mPaint);

        //还原混合模式
        mPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(sc);
*/
        /**
         * 3. 滤色的使用
         * Mode.SCREEN
         */
        canvas.drawColor(Color.WHITE);
        /**
         * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
         */
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        //先绘制一层颜色
        canvas.drawColor(0xcc1c093e);

        //设置混合模式
        mPaint.setXfermode(porterDuffXfermode);//DST_OUT

        //再绘制src源图
        canvas.drawBitmap(bitmapDis, x, y, mPaint);

        //还原混合模式
        mPaint.setXfermode(null);

        //还原画布
        canvas.restoreToCount(sc);


        //绘制美女图
//        canvas.drawBitmap(bitmapDis, x, y, mPaint);
    }
}
