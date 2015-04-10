package com.aliao.cvtraining.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/10.
 * 爱哥：http://blog.csdn.net/aigestudio/article/details/41316141
 * http://www.cnblogs.com/leon19870907/articles/1978065.html
 * http://blog.csdn.net/harvic880925/article/details/38875149
 */
public class CustomColorFilterView extends View {

    private Paint mPaint;
    private Context mCtx;
    private Bitmap bitmap;
    private int x, y;

    public CustomColorFilterView(Context context) {
        super(context);
    }

    public CustomColorFilterView(Context context, AttributeSet attrs) {
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
     * 初始化资源
     */
    private void initRes() {

        bitmap = BitmapFactory.decodeResource(mCtx.getResources(), R.mipmap.a);
        /**
         * 计算位图绘制时左上角的坐标，使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */

        x = MeasureUtil.getScreenSize((Activity) mCtx)[0] / 2 - bitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mCtx)[1] / 2 - bitmap.getHeight() / 2;
    }

    private void initPaint() {
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
        mPaint.setStyle(Paint.Style.FILL);

        /**
         * 设置画笔颜色为自定义颜色
         */
//        mPaint.setColor(Color.argb(225, 225, 128, 103));

        /**
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrockWidth(0)的时候描边宽度并不为0而只是占了一个像素
         */
        mPaint.setStrokeWidth(10);

        /**
         * 生成彩色矩阵
         * 这个矩阵不同的位置标识的RGBA值，其范围在0.0F至2.0F之间，1为保持原图的RGB值
         */
/*
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));*/
        /**
         * 绘制图片，修改颜色矩阵一
         */
        /*ColorMatrix colorMatrix1 = new ColorMatrix(new float[]{
                0.5F, 0, 0, 0, 0,
                0, 0.5F, 0, 0, 0,
                0, 0, 0.5F, 0, 0,
                0, 0, 0, 0.5F, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix1));*/
        /**
         * 绘制图片，修改颜色矩阵二（变灰）
         */
        ColorMatrix colorMatrix2 = new ColorMatrix(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        });
        /**
         * 绘制图片，修改颜色矩阵三（相反效果）
         */
        ColorMatrix colorMatrix3 = new ColorMatrix(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        });
        /**
         * 绘制图片，修改颜色矩阵四（红色的变成了蓝色而蓝色的就变成了红色）
         */
        ColorMatrix colorMatrix4 = new ColorMatrix(new float[]{
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });
        /**
         * 绘制图片，修改颜色矩阵五（老旧照片）
         */
        ColorMatrix colorMatrix5 = new ColorMatrix(new float[]{
                0.393F, 0.769F, 0.189F, 0, 0,
                0.349F, 0.686F, 0.168F, 0, 0,
                0.272F, 0.534F, 0.131F, 0, 0,
                0, 0, 0, 1, 0,
        });
        /**
         * 绘制图片，修改颜色矩阵六（去色后高对比度）
         */
        ColorMatrix colorMatrix6 = new ColorMatrix(new float[]{
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                0, 0, 0, 1, 0,
        });
        /**
         * 绘制图片，修改颜色矩阵七（饱和度对比度加强）
         */
        ColorMatrix colorMatrix7 = new ColorMatrix(new float[]{
                1.438F, -0.122F, -0.016F, 0, -0.03F,
                -0.062F, 1.378F, -0.016F, 0, 0.05F,
                -0.062F, -0.122F, 1.483F, 0, -0.02F,
                0, 0, 0, 1, 0,
        });
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix7));

        /**
         * 光照颜色过滤
         * 参数mul全称是colorMultiply意为色彩倍增，而add全称是colorAdd意为色彩添加，这两个值都是16进制的色彩值0xAARRGGBB
         */
        mPaint.setColorFilter(new LightingColorFilter(0xFFFF00FF, 0x00000000));


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆形
//        canvas.drawCircle(MeasureUtil.getScreenSize((Activity) mCtx)[0] / 2, MeasureUtil.getScreenSize((Activity) mCtx)[1] / 2, 200,mPaint);

        //绘制位图
        canvas.drawBitmap(bitmap, x, y, mPaint);
    }
}
