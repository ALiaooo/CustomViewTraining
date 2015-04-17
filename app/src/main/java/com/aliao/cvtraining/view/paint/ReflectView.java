package com.aliao.cvtraining.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/16.
 */
public class ReflectView extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap, mRefBitmap;
    private int x, y;//绘制位图的起点坐标
    private PorterDuffXfermode mXfermode;

    public ReflectView(Context context) {
        super(context);
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化画笔
         */
        initPaint();
        initRes(context);

    }

    /**
     * 初始化资源
     * @param context
     */
    private void initRes(Context context) {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gril);

        /**
         * 实例化一个矩阵对象
         */
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        /**
         * 生成倒影图
         */
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0,mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix,true);

        int screenX = MeasureUtil.getScreenSize((android.app.Activity) context)[0];
        int screenY = MeasureUtil.getScreenSize((android.app.Activity) context)[1];

        x = screenX/2 - mSrcBitmap.getWidth()/2;
        y = screenY/2 - mSrcBitmap.getHeight();

        /**
         * 要在倒立的图片上加上线性渐变效果，首先要设置和图片一样大小的矩形上设置这种渐变效果，然后将这个效果放到图片上，所以要用到混合模式
         */
        mPaint.setShader(new LinearGradient(screenX/2, y+mSrcBitmap.getHeight(), screenX/2, y+mSrcBitmap.getHeight()*2, 0xAA000000, Color.TRANSPARENT, Shader.TileMode.CLAMP));

        /**
         * 设置混合模式是PorterDuff.Mode.DST_IN，只在两者相交的地方，显示上层图像。这里的两图像是重合的，且上层图像是透明的，所以会透过上层透明图像看到底部的图像，好赞
         */
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //黑色背景
        canvas.drawColor(Color.BLACK);

        /**
         * 绘制原图
         */
        canvas.drawBitmap(mSrcBitmap, x, y, null);
//        canvas.drawBitmap(mRefBitmap, x, y+mSrcBitmatHeight(),null);

        /**
         * 使用混合模式绘制倒影图像
         */
//       将绘制操作保存到新的图层（更官方的说应该是离屏保存）
         int sc = canvas.saveLayer(x, y+mSrcBitmap.getHeight(),  x+mSrcBitmap.getWidth(), y+mSrcBitmap.getHeight()*2, null, Canvas.ALL_SAVE_FLAG);
        //先绘制dst图（下）
        canvas.drawBitmap(mRefBitmap,x, y+mSrcBitmap.getHeight(), null);
        //设置混合模式
        mPaint.setXfermode(mXfermode);
        //绘制src图 黑色到透明色的渐变（上）
        canvas.drawRect(x,y+mSrcBitmap.getHeight(), x +mSrcBitmap.getWidth(),y+mSrcBitmap.getHeight()*2,mPaint);
        //还原混合模式
        mPaint.setXfermode(null);
        //还原画布
        canvas.restoreToCount(sc);
    }
}
