package com.aliao.cvtraining.view.paint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.MeasureUtil;

import java.util.Arrays;

/**
 * Created by 丽双 on 2015/4/16.
 */
public class ShaderView extends View {

    private static final int RECT_SIZE = 200;
    private Paint mPaint;
    private Bitmap bitmap;
    private int x, y;//绘制位图时左上角的坐标
    private int left, top, right, bottom;//
    private int screenW, screenH;


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
         * 3. SweepGradient 扫描式渐变
         *    其效果有点类似雷达的扫描效果，他也有两个构造方法
         * 4. RadialGradient 径向渐变 径向渐变说的简单点就是个圆形中心向四周渐变的效果，也有两个构造方法
         * 5. ComposeShader 组合着色器， 就是两个Shader组合在一起作为一个新Shader。
         *    他有两个构造函数，两个都差不多的，只不过一个指定了只能用PorterDuff的混合模式而另一个只要是Xfermode下的混合模式都没问题
         */
//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));//图像着色器
//        mPaint.setShader(new LinearGradient(0,RECT_SIZE,RECT_SIZE,RECT_SIZE, Color.RED, Color.YELLOW, Shader.TileMode.REPEAT));//线性渐变
//        mPaint.setShader(new LinearGradient(left,top, right, bottom,new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN},new float[]{0, 0.5F, 0.7F, 1.0F}, Shader.TileMode.REPEAT));

//        mPaint.setShader(new SweepGradient(screenW/2, screenH/2, Color.RED, Color.YELLOW));//扫描着色器
//        mPaint.setShader(new SweepGradient(screenW/2, screenH/2, new int[]{Color.RED, Color.YELLOW, Color.BLUE}, new float[]{0, 0.5F, 1.0F}));

//        mPaint.setShader(new RadialGradient(screenW/2, screenH/2, 100, Color.WHITE, Color.TRANSPARENT, Shader.TileMode.REPEAT));//径向渐变
//        mPaint.setShader(new RadialGradient(screenW/2, screenH/2, 50, new int[]{ Color.WHITE, Color.CYAN , Color.TRANSPARENT}, new float[]{0, 0.5F, 1.0F}, Shader.TileMode.REPEAT));


        /**
         * 画笔设置着色器前为我们的着色器设置了一个变换矩阵，让我们的Shader依据自身的坐标→平移left个单位↓平移top个单位，
         * 也就是说原本shader的原点应该是画布（注意不是屏幕！这里只是刚好画布更屏幕重合了而已！切记！）的左上方[0,0]的位置，
         * 通过变换移至了[left,top]的位置，如果没问题，Shader此时应该是刚好是从我们矩形的左上方开始着色
         */

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);


        /*************************************************************
         * 测试matrix
         */

        /**
         * 实例一个矩阵对象
         * 原始矩阵：
         *  [1, 0, 0]
            [0, 1, 0]
            [0, 0, 1]
         */
        Matrix matrix = new Matrix();
        // 设置矩阵变换
//        matrix.setTranslate(left, top);//Shader此时刚好是从我们矩形的左上方开始着色

        //显示矩阵中的值
        printMatrixValue(matrix);

        /**
         * 旋转5度
         * Android中Matrix的pre post set方法理解:
         * Matrix调用一系列set,pre,post方法时,可视为将这些方法插入到一个队列.当然,按照队列中从头至尾的顺序调用执行.
         * 其中pre表示在队头插入一个方法,post表示在队尾插入一个方法.而set表示把当前队列清空,并且总是位于队列的最中间位置.
         * 当执行了一次set后:pre方法总是插入到set前部的队列的最前面,post方法总是插入到set后部的队列的最后面
         * 矩阵乘法概念：
         * 左乘即前乘就是乘在左边（在乘号的前面）例如A左乘E即为AE
         * pre方法表示矩阵前乘，例如：变换矩阵为A，原始矩阵为B，pre方法的含义即是A*B
         * post方法表示矩阵后乘，例如：变换矩阵为A，原始矩阵为B，post方法的含义即是B*A
         * http://www.linuxidc.com/Linux/2012-07/65035.htm、
         * matrix.setRotate(5);,set方法一旦调用即会清空之前matrix中的所有变换
         * matrix.preRotate(5); 矩阵（matrix）先乘
         * matrix.postRotate(5);矩阵（matrix）后乘
         * eg:
         * Matrix的初始值：
             [sx, k1, 0]
             [k2, sy, 0]
             [0,   0,  1]
             setTranslate(2, 3)后：
             [sx, k1, 2]
             [k2, sy, 3]
             [0,   0,  1]
             上面set后，再preTranslate(4, 5) 原始矩阵前乘：
             [sx, k1, 2][1, 0, 4]   [sx, k1, sx*4+k1*5+2]
             [k2, sy, 3][0, 1, 5] = [k2, sy, k2*4+sy*5+3]
             [0,   0, 1][0, 0, 1]   [0,  0,  1]
             上面set后，再postTranslate(4, 5)原始矩阵后乘：
             [1, 0, 4][sx, k1, 2]   [sx, k1, 2+4]
             [0, 1, 5][k2, sy, 3] = [k2, sy, 5+3]
             [0, 0, 1][0,   0, 1]   [0,   0,  1]

         matrix.setTranslate(500, 500);新值覆盖原值，变为500
         matrix.postTranslate(500, 500);直接加上新值，变为原值+500
         matrix.preTranslate(500, 500);

         * 怎么用？
         * http://blog.csdn.net/linmiansheng/article/details/18820599
         */

        /**
         * 设置平移，新值覆盖原值，原始矩阵变为：
         *  [1, 0, 200]
            [0, 1, 200]
            [0, 0, 1]
         */
        matrix.setTranslate(200, 100);
        //显示矩阵中的值
        printMatrixValue(matrix);

        matrix.setScale(2,2);
        printMatrixValue(matrix);
        /**
         * 原始矩阵左乘变换矩阵
         *  [1, 0, 200]   [1, 0, 200]
            [0, 1, 200] * [0, 1, 100]
            [0, 0, 1]     [0, 0, 1]
         */
//        matrix.preTranslate(50, 200);
        /**
         * 原始矩阵右乘变换矩阵
         *  [1, 0, 200]   [1, 0, 200]
            [0, 1, 100] * [0, 1, 200]
            [0, 0, 1]     [0, 0, 1]
         */
        matrix.postTranslate(50, 200);
        //显示矩阵中的值
        printMatrixValue(matrix);
//        matrix.postRotate(5);
/*        //显示矩阵中的值
        float[] values = new float[9];
        matrix.getValues(values);
        for (int i=0; i<values.length; i++){
            L.d(i+ " = "+values[i]);
        }*/


        /*************************************************************/
        // 设置Shader的变换矩阵
        bitmapShader.setLocalMatrix(matrix);
        // 设置着色器
        mPaint.setShader(bitmapShader);

    }

    private void printMatrixValue(Matrix matrix){
        float[] values = new float[9];
        matrix.getValues(values);
        L.d(" Matrix = "+ Arrays.toString(values));
    }


    private void initRes(Context context) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        screenW = MeasureUtil.getScreenSize((Activity) context)[0];
        screenH = MeasureUtil.getScreenSize((Activity) context)[1];
        x = screenW/2 - RECT_SIZE/2;
        y = screenH/2 - RECT_SIZE/2;
        L.d("screenSizeX = "+screenW+", screenSizeY = "+screenH);
        left = screenW/2 - RECT_SIZE;
        right = screenW/2 + RECT_SIZE;
        top = screenH/2 - RECT_SIZE;//0;
        bottom = screenH/2 + RECT_SIZE;
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
//        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);

        /**
         * 扫描渐变，渐变区域落在绘制的矩形区域内部
         */

//        canvas.drawRect(left, top, right, bottom, mPaint);

        /**
         * Matrix测试
         */
        canvas.drawRect(0, 0, screenW, screenH, mPaint);
    }
}
