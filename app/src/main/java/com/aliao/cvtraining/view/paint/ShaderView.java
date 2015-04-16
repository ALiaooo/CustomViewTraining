package com.aliao.cvtraining.view.paint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.MeasureUtil;

/**
 * Created by 丽双 on 2015/4/16.
 */
public class ShaderView extends View {

    private static final int RECT_SIZE = 200;
    private Paint mPaint;
    private Bitmap bitmap;
    private int x, y;//绘制位图时左上角的坐标
    private int left, top, right, bottom;//


    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, AttributeSet attrs) {
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

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        /**
         * 设置着色器
         * 有三种模式：
         *  Shader.TileMode.CLAMP：边缘第一个像素拉伸
         *  Shader.TileMode.REPEAT：重复
         *  Shader.TileMode.MIRROR：镜像
         ****************************************************************
         * 据观察，图片都是从整个屏幕的左上角开始画，比如重复模式，图片从左上角开始绘图，然后再xy轴重复，当我们在onDraw里画矩形时，就相当于是在图片的背景下截取的一个矩形区域作为观看窗口
         * 并不是指的在所绘制的矩形区域里去重复这张图片，BrickView手指触摸屏幕时可以看到很奇特的效果。
         * **************************************************************
         * 着色器种类：
         * 1. BitmapShader
         * 2. LinearGradient 线性渐变
         *    线性渐变的方向用坐标控制，如果x坐标相等，y坐标不等，是垂直方向的渐变；如果x坐标不等，y坐标相等，是横向的渐变；如果x坐标和y坐标不等，那么两个坐标围起来的是一个矩形，就是径向渐变即对角线渐变方向。
         *    ositions表示的是渐变的相对区域，其取值只有0到1，上面的代码中我们定义了一个[0, 0.1F, 0.5F, 0.7F, 0.8F]，意思就是红色到黄色的渐变起点坐标在整个渐变区域（left, top, right, bottom定义了渐变的区域）的起点，
         *    而终点则在渐变区域长度 * 10%的地方，而黄色到绿色呢则从渐变区域10%开始到50%的地方以此类推，positions可以为空
         *    使用可以看ReflectView- 图片倒影效果
         *
         * 3.
         */
//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
//        mPaint.setShader(new LinearGradient(0,RECT_SIZE,RECT_SIZE,RECT_SIZE, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT));
        mPaint.setShader(new LinearGradient(left,top, right, bottom,new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN},new float[]{0, 0.5F, 0.7F, 1.0F}, Shader.TileMode.REPEAT));

    }


    private void initRes(Context context) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);

        x = MeasureUtil.getScreenSize((Activity) context)[0]/2 - RECT_SIZE/2;
        y = MeasureUtil.getScreenSize((Activity) context)[1]/2 - RECT_SIZE/2;
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);
        L.d("screenSizeX = "+screenSize[0]+", screenSizeY = "+screenSize[1]);
        left = screenSize[0]/2 - RECT_SIZE;
        right = screenSize[0]/2 + RECT_SIZE;
        top = screenSize[1]/2 - RECT_SIZE;//0;
        bottom = screenSize[1]/2 + RECT_SIZE;


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawBitmap(bitmap,x, y, mPaint);
        /**
         * 正常设置
         * 线性渐变时，渐变区域整好落在绘制的矩形区域
         */
//        canvas.drawRect(left, top, right, bottom, mPaint);
        /**
         * 线性渐变，渐变区域落在绘制的矩形区域内部
         */
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);
    }
}
