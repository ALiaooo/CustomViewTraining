package com.aliao.cvtraining.view.paint;

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
import com.aliao.cvtraining.bo.PorterDuffBO;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/13.
 *
 * PorterDuff.Mode.ADD 饱和相加，对图像饱和度进行相加
 * PorterDuff.Mode.CLEAR 清除图像
 * PorterDuff.Mode.DARKEN 变暗
 * 两个图像混合，较深的颜色总是会覆盖较浅的颜色，如果两者深浅相同则混合，如图，黄色覆盖了红色而蓝色和青色因为是跟透明混合所以不变。
 * 细心的朋友会发现青色和黄色之间有一层类似橙色的过渡色，这就是混合的结果。在实际的测试中源图和目标图的DARKEN混合偶尔会有相反的结果比如红色覆盖了黄色，
 * 这源于Android对颜色值“深浅”的定义。
 *
 * PorterDuff.Mode.DST 只绘制目标图像
 * PorterDuff.Mode.DST_ATOP 在源图像和目标图像相交的地方绘制目标图像而在不相交的地方绘制源图像
 * PorterDuff.Mode.DST_IN 只在源图像和目标图像相交的地方绘制目标图像,取两层绘制交集。显示下层。
 * PorterDuff.Mode.DST_OUT 只在源图像和目标图像不相交的地方绘制目标图像
 * PorterDuff.Mode.DST_OVER 在源图像的上方绘制目标图像,目标图像在上
 * PorterDuff.Mode.LIGHTEN 变亮
 * PorterDuff.Mode.MULTIPLY 正片叠底,源图像素颜色值乘以目标图像素颜色值除以255即得混合后图像像素的颜色值，该模式在设计领域应用广泛，因为其特性黑色与任何颜色混合都会得黑色，在手绘的上色、三维动画的UV贴图绘制都有应用
 * PorterDuff.Mode.OVERLAY 叠加，这个模式没有在官方的API DEMO中给出，谷歌也没有给出其计算方式，在实际效果中其对亮色和暗色不起作用，也就是说黑白色无效，它会将源色与目标色混合产生一种中间色，这种中间色生成的规律也很简单，如果源色比目标色暗，那么让目标色的颜色倍增否则颜色递减
 * PorterDuff.Mode.SCREEN 滤色，计算方式我不解释了，滤色产生的效果我认为是Android提供的几个色彩混合模式中最好的，它可以让图像焦媃幻化，有一种色调均和的感觉
 * PorterDuff.Mode.SRC 显示源图
 * PorterDuff.Mode.SRC_ATOP 在源图像和目标图像相交的地方绘制源图像，在不相交的地方绘制目标图像
 * PorterDuff.Mode.SRC_IN 只在源图像和目标图像相交的地方绘制源图像。取两层绘制交集。显示上层。
 * PorterDuff.Mode.SRC_OUT 只在源图像和目标图像不相交的地方绘制源图像。 取上层绘制非交集部分。
 * PorterDuff.Mode.SRV_OVER 在目标图像的顶部绘制源图像,源图像在上
 * PorterDuff.Mode.XOR 在源图像和目标图像重叠之外的任何地方绘制他们，而在不重叠的地方不绘制任何内容
 */
public class PoterDuffView extends View {

    private Paint mPaint;
    private PorterDuffBO  porterDuffBO;
    private PorterDuffXfermode porterDuffXfermode;//图形混合模式

    private static final PorterDuff.Mode MODE = PorterDuff.Mode.SRC_IN;
    private static final int RECT_SIZE_SMALL = 300;// 左右上方示例渐变正方形的尺寸大小
    private static final int RECT_SIZE_BIG = 600;// 中间测试渐变正方形的尺寸大小

    private int screenW, screenH;// 屏幕尺寸
    private int s_l, s_t;// 左上方正方形的原点坐标
    private int d_l, d_t;// 右上方正方形的原点坐标
    private int rectX, rectY;// 中间正方形的原点坐标

    public PoterDuffView(Context context) {
        super(context);
    }

    public PoterDuffView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 初始化画笔
         */
        initPaint();

        //实例化业务对象
        porterDuffBO = new PorterDuffBO();

        //实例化混合模式
        porterDuffXfermode = new PorterDuffXfermode(MODE);
        //计算坐标
        calu(context);

    }

    /**
     * 计算坐标
     * @param context
     */
    private void calu(Context context) {
        // 获取包含屏幕尺寸的数组
        int[] screenSize =  MeasureUtil.getScreenSize((Activity) context);
        //获取屏幕尺寸
        screenW = screenSize[0];
        screenH = screenSize[1];

        //计算左上方正方形远点坐标
        s_l = 0;
        s_t = 0;

        //计算右上方正方形原点坐标
        d_l = screenW - RECT_SIZE_SMALL;
        d_t = 0;

        //计算中间正方形原点坐标
        rectX = screenW/2 -RECT_SIZE_BIG/2;
        rectY = RECT_SIZE_SMALL + (screenH - RECT_SIZE_SMALL) / 2 - RECT_SIZE_BIG / 2;

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画布颜色为黑色，以便更好观察
        canvas.drawColor(Color.BLACK);

        //设置业务对象尺寸值计算生成左右上方的渐变方形
        porterDuffBO.setSize(RECT_SIZE_SMALL);

        /**
         * 画出右上方两个正方形
         * 其中左边的为src，右边的为dis
         * Src为源图像，意为将要绘制的图像；Dis为目标图像，意为我们将要把源图像绘制到的图像
         */
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(),s_l, s_t, mPaint);
        canvas.drawBitmap(porterDuffBO.initDisBitmap(),d_l, d_t, mPaint);

        /**
         * 将绘制操作保存到新的图层（更官方的说应该是离屏保存）
         */
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        //重新设置业务对象尺寸值计算生成中间的渐变方形
        porterDuffBO.setSize(RECT_SIZE_BIG);
        //先绘制dis目标图
        canvas.drawBitmap(porterDuffBO.initDisBitmap(), rectX, rectY, mPaint);
        //设置混合模式
        mPaint.setXfermode(porterDuffXfermode);
        //再绘制src源图
        canvas.drawBitmap(porterDuffBO.initSrcBitmap(), rectX, rectY, mPaint);
        //还原混合模式
        mPaint.setXfermode(null);
        //还原画布
        canvas.restoreToCount(sc);
    }
}
