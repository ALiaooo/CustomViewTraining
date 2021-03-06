package com.aliao.cvtraining.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/17.
 * http://blog.csdn.net/aigestudio/article/details/41799811
 */
public class DreamEffectView extends View {

    private Paint mPaint, mShaderPaint;
    private Bitmap mBitmap, darkConnerBitemap;
    private int x, y;//位图绘制起点坐标
    private int screenW, screenH;//屏幕宽高
    private PorterDuffXfermode mXfermode;

    public DreamEffectView(Context context) {
        super(context);
    }

    public DreamEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化资源
         */
        initRes(context);

        /**
         * 初始化画笔
         */
        initPaint();

    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gril);

        screenW = MeasureUtil.getScreenSize((android.app.Activity) context)[0];
        screenH = MeasureUtil.getScreenSize((android.app.Activity) context)[1];

        x = screenW/2 - mBitmap.getWidth()/2;
        y = screenH/2 - mBitmap.getHeight()/2;

    }

    private void initPaint() {
        mPaint = new Paint();

        /**
         * 设置色彩矩阵——去饱和、提亮、色相矫正
         */
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0.8587F, 0.2940F, -0.0927F, 0, 6.79F,
                0.0821F, 0.9145F, 0.0634F, 0, 6.79F,
                0.2019F, 0.1097F, 0.7483F, 0, 6.79F,
                0, 0, 0, 1, 0
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

        /**
         * 实例化一个混合模式
         */
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);


        /**
         * 实例化一个画笔,设置shader
         */
        mShaderPaint = new Paint();
//        mShaderPaint.setShader(new RadialGradient(screenW/2, screenH/2, mBitmap.getHeight()*7/8, Color.TRANSPARENT, Color.BLACK, Shader.TileMode.MIRROR));

        /***********************************************************
         * 椭圆形渐变暗角
         */
        //分局我们源图的大小生成暗角Bitmap
        darkConnerBitemap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //将该暗角的Bitmap注入Canvas
        Canvas canvas = new Canvas(darkConnerBitemap);

        //计算径向渐变半径
        float radiu = canvas.getHeight()*2/3;

        //实力化径向渐变
        RadialGradient radialGradient = new RadialGradient(canvas.getWidth() / 2F, canvas.getHeight() / 2F, radiu, new int[] { 0, 0, 0xAA000000 }, new float[] { 0F, 0.7F, 1.0F }, Shader.TileMode.CLAMP);

        //实例化一个矩阵
        Matrix matrix = new Matrix();
        //设置矩阵的缩放
        matrix.setScale(canvas.getWidth()/(radiu*2F), 1.0F);

        //设置矩阵的预平移
        matrix.preTranslate((radiu * 2F) - canvas.getWidth()/2F, 0);

        //将该矩阵注入径向渐变
        radialGradient.setLocalMatrix(matrix);

        //设置画笔shader
        mShaderPaint.setShader(radialGradient);

        //绘制矩形
        canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), mShaderPaint);

        /***********************************************************/

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //新建图层
        int sc = canvas.saveLayer(x, y, x+mBitmap.getWidth(), y+mBitmap.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        //绘制混合颜色（dst）
        canvas.drawColor(0xcc1c093e);
        //设置混合模式
        mPaint.setXfermode(mXfermode);
        //绘制src图像
        canvas.drawBitmap(mBitmap, x, y, mPaint);
        //还原混合模式
        mPaint.setXfermode(null);
        //还原混合模式
        canvas.restoreToCount(sc);

//        canvas.drawRect(x, y,x+mBitmap.getWidth(), y+mBitmap.getHeight(), mShaderPaint);

        // 绘制我们画好的径向渐变图
        canvas.drawBitmap(darkConnerBitemap, x, y, null);
    }
}
