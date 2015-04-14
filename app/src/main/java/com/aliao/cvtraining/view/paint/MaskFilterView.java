package com.aliao.cvtraining.view.paint;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/14.
 */
public class MaskFilterView extends View {

    private static final int RECT_SIZE = 600;
    private Paint mPaint, shadowPaint;
    private int left, right, top, bottom;
    private Bitmap srcBitmap,shadowBitmap;//位图和阴影位图
    private int x, y;//位图绘制时左上角的起点坐标

    public MaskFilterView(Context context) {
        super(context);
    }

    public MaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化画笔
         */
        initPaint();
        /**
         * 初始化资源
         */
        initRes(context);
    }

    private void initRes(Context context) {

        /**
         * 计算位图时左上角的坐标使其位于屏幕中心
         */
        left = MeasureUtil.getScreenSize((Activity)context)[0] / 2 - RECT_SIZE / 2;
        top = MeasureUtil.getScreenSize((Activity)context)[1] / 2 - RECT_SIZE / 2;
        right = left+RECT_SIZE;
        bottom = top + RECT_SIZE;

        //获取位图
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        //获取位图的alpha通道图
        shadowBitmap = srcBitmap.extractAlpha();
        /**
         * 位图绘制时左上角的起点坐标
         */
        x =  MeasureUtil.getScreenSize((Activity)context)[0] / 2 - srcBitmap.getWidth() / 2;
        y =  MeasureUtil.getScreenSize((Activity)context)[1] / 2 - srcBitmap.getHeight() / 2;
    }

    /**
     * 初始化画笔
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void initPaint() {
        /**
         * 关闭单个view的硬件加速
         */
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF603811);

        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.DKGRAY);
        /**
         * 设置画笔遮罩滤镜
         * 第一个参数：radius值越大，阴影越扩散
         * 第二个参数 模糊的类型，总共有四种：
         * Blur.NORMAL 将整个图像模糊掉
         * Blur.SOLID 图像的Alpha边界外产生一层与Paint颜色一致的阴影效果而不影响图像本身
         * Blur.OUTER 会在Alpha边界产生一层阴影且会将原本的图像变透明
         * Blur.INNER 在图像内部产生模糊
         *
         * 。如上所说BlurMaskFilter是根据Alpha通道的边界来计算模糊的，如果是一张图片
         * （注：上面我们说过Android会把拷贝到资源目录的图片转为RGB565，具体原因具体分析我会单独开一篇帖子说，这里就先假设所有提及的图片格式为RGB565）
         * 你会发现没有任何效果，那么假使我们需要给图片加一个类似阴影的效果该如何做呢？其实很简单，我们可以尝试从Bitmap中获取其Alpha通道，
         * 并在绘制Bitmap前先以该Alpha通道绘制一个模糊效果不就行了？
         */
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        shadowPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画一个矩形
         */
//        canvas.drawRect(left, top, right, bottom, mPaint);

        /**
         * 先绘制阴影再绘制位图
         */
        canvas.drawBitmap(shadowBitmap, x, y,shadowPaint);
        canvas.drawBitmap(srcBitmap, x, y, null);
    }
}
