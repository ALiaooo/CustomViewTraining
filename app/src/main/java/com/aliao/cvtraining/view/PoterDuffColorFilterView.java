package com.aliao.cvtraining.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/13.
 */
public class PoterDuffColorFilterView extends View {

    private Paint mPaint;
    private Bitmap bitmap;
    private int x, y;//位图绘制时左上角的起始坐标
    private Context mCtx;

    public PoterDuffColorFilterView(Context context) {
        super(context);
        mCtx = context;
    }

    public PoterDuffColorFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
        /**
         * 初始化画笔
         */
        initPaint();
        /**
         * 初始化资源
         */
        initRes();
    }



    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();

        mPaint.setAntiAlias(true);

        /**
         * 图像色彩混合和图形色彩混合(PorterDuffXfermode图形混合模式)
         */
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
    }

    /**
     * 初始化资源
     */
    private void initRes() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);

        x = MeasureUtil.getScreenSize((Activity) mCtx)[0]/2 - bitmap.getWidth()/2;

        y = MeasureUtil.getScreenSize((Activity) mCtx)[1]/2 + bitmap.getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘图
        canvas.drawBitmap(bitmap, x, y, mPaint);
    }
}
