package com.aliao.cvtraining.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 丽双 on 2015/4/14.
 */
public class PathEffectView extends View {
    private float mPhase;//偏移值

    private Paint mPaint;
    private Path mPath;
    private PathEffect[] mEffects;

    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(5);

        //实例化路径
        mPath = new Path();
        //定义路径的起点
        mPath.moveTo(0, 0);
        //定义路径的各个点
        for (int i = 0; i<=30; i++){
            mPath.lineTo(i*35, (float) (Math.random() * 100));
        }

        //创建路径效果数组
        mEffects = new PathEffect[7];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 实例化各类特效
         */
        mEffects[0] = null;
        /**
         * radius 转角处的圆滑程度
         * 将两个线段连接处的尖角替换成圆角，圆角程度又radius来指定
         */
        mEffects[1] = new CornerPathEffect(20);

        /**
         * 离散路径效果
         * 第一个参数用来指定切割路径的长度——杂点的长度，值越大，杂点越少越大，值越小，杂点越小越多（越密集）
         * 第二个参数用来指定偏离原来路径的离散程度，值越大，偏离原路径的离散度越大，反之则越小
         */
        mEffects[2] = new DiscretePathEffect(3.0F, 5.0F);//第一个参数segment分割长度，段长；第二个参数deviation误差
        /**
         * 虚线路径效果
         * interval间隔数组，其长度必须为偶数，数组元素个数大于等于2
         * 偶数位的数为on，奇数位的数为off。on表述实线，off表示实线段之间的间隔，虚空的部分
         * interval间隔数组控制虚线的的长度，画笔的paint's strokeWidth控制虚线的粗细
         * 该路径效果只对paint的style是STROKE or FILL_AND_STROKE有效，对style == FILL没有效果
         * 第二个参数mPhase称之为偏移值
         *
         * 而float[] {20, 10}的偶数参数20（注意数组下标是从0开始哦）定义了我们第一条实线的长度，而奇数参数10则表示第一条虚线的长度，
         * 如果此时数组后面不再有数据则重复第一个数以此往复循环，比如我们20,10后没数了，那么整条线就成了[20,10,20,10,20,10…………………………]这么一个状态
         */
        mEffects[3] = new DashPathEffect(new float[]{50, 20}, mPhase);
        /**
         * 用指定的形状来虚线化路径
         * 当画笔风格是STROCK或者FILL_AND_STROCK时有效，Paint Style为FILL时忽略，Paint的strokeWidth不会影响效果
         * advance表示每个图形间的间距
         * phrase为绘制时的偏移量
         * style 转变每个位置上的形状,总共有三种：
         * PathDashPathEffect.Style.TRANSLATE: 图形会以位置平移的方式与下一段相连接
         * PathDashPathEffect.Style.ROTATE: 线段连接处的图形转换以旋转到与下一段移动方向相一致的角度进行转转
         * PathDashPathEffect.Style.MORPH: 图形会以发生拉伸或压缩等变形的情况与下一段相连接
         */
        Path path = new Path();
        path.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mEffects[4] = new PathDashPathEffect(path, 20, mPhase, PathDashPathEffect.Style.ROTATE);
        /**
         * 组合效果
         * 首先会将innerpe表现出来，再在innerpe的基础上增加outerpe的效果
         */
        mEffects[5] = new ComposePathEffect(mEffects[2],mEffects[4]);
        /**
         * 叠加效果
         * 分别对两个参数的效果各自独立表现出来，然后将两个效果简单的重叠在一起显示出来
         */
        mEffects[6] = new SumPathEffect(mEffects[2], mEffects[3]);
        /**
         * 绘制路径
         */
        for (int i = 0; i<mEffects.length; i++){
            mPaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath, mPaint);

            //每绘制一条将画布向下平移250个像素
            canvas.translate(0, 180);
        }

        // 刷新偏移值并重绘视图实现动画效果
//        mPhase += 1;
//        invalidate();

    }
}
