package com.aliao.cvtraining.view.font;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.MeasureUtil;

import java.lang.reflect.Type;

/**
 * Created by 丽双 on 2015/4/14.
 */
public class FontView extends View {

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊ";//ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓

    private Paint mPaint;//文本的画笔
    private Paint mLinePaint;//中心线的画笔
    private Paint.FontMetrics mFontMetrics;
    private int baseX, baseY;

    public FontView(Context context) {
        super(context);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setTextSize(50);
        mPaint.setTextSize(70);//重设字体大小为70
        mPaint.setColor(Color.BLACK);
//        mPaint.setTypeface(Typeface.SERIF);//更改字体
        /**
         * 创建字体
         */
//        mPaint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));

        /**
         * 文本在水平方向的倾斜，这个倾斜值没有具体的范围，但是官方推崇的值为-0.25可以得到比较好的倾斜文本效果，值为负右倾，值为正左倾，默认值为0
         */
//        mPaint.setTextSkewX(-0.25F);
        /**
         * 将文本沿X轴水平方向缩放，默认值为1，当值大于1会沿X轴水平方向放大文本，当小于1会沿X轴水平方向收缩文本
         */
//        mPaint.setTextScaleX(1.5F);

        /**
         * 设置是否打开文本的亚像素显示，什么叫亚像素显示呢？你可以理解为对文本显示的一种优化技术，
         * 如果大家用的是Win7+系统可以在控制面板中找到一个叫ClearType的设置，该设置可以让你的文本更好地显示在屏幕上就是基于亚像素显示技术
         */
//        mPaint.setSubpixelText(true);
        /**
         * 设置文本删除线
         */
        mPaint.setStrikeThruText(false);

        /**
         * 设置是否打开线性文本标识，这玩意对大多数人来说都很奇怪不知道这玩意什么意思。想要明白这东西你要先知道文本在Android中是如何进行存储和计算的。
         * 在Android中文本的绘制需要使用一个bitmap作为单个字符的缓存，既然是缓存必定要使用一定的空间，我们可以通过setLinearText (true)告诉Android我们不需要这样的文本缓存。
         */
//        mPaint.setLinearText(false);

        /**
         * 设置文本仿粗体
         */
//        mPaint.setFakeBoldText(true);

        /******************************************************************************************
         * 测量文本宽度
         * mPaint.measureText(TEXT)和 mPaint.getTextBounds获取的宽度不一样，他们的到底有什么区别？*
         ******************************************************************************************/
        mPaint.measureText(TEXT);
        L.d("mPaint.measureText(TEXT) = "+mPaint.measureText(TEXT));
        Rect rect = new Rect();
        mPaint.getTextBounds(TEXT, 0, TEXT.length(), rect);
        L.d("rect.width() = "+rect.width());

        /**
         * 绘制图形时的抗抖动
         */
//        mPaint.setDither(true);

        /**
         * 该方法的用法在MaskFilterView类里来展示
         */
//        mPaint.setMaskFilter();

        mFontMetrics = mPaint.getFontMetrics();



        /**
         * Baseline以上为负数，baseline以下为正数
         * 从代码中我们可以看到一个很特别的现象，在我们绘制文本之前我们便可以获取文本的FontMetrics属性值，
         * 也就是说我们FontMetrics的这些值跟我们要绘制什么文本是无关的，而仅与绘制文本Paint的size和typeface有关
         * 测试：
         * 1.调整paint的字体大小
         * 2.设置字体
         */
        L.d("font top = "+mFontMetrics.top);
        L.d("font ascent = "+mFontMetrics.ascent);
        L.d("font descent = "+mFontMetrics.descent);
        L.d("font bottom = "+mFontMetrics.bottom);
        L.d("font leading = "+mFontMetrics.leading);

        /**
         * 实例化中心线画笔
         */
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //计算baseline绘制的起始x轴坐标
//        baseX = (int) (canvas.getWidth() / 2 - mPaint.measureText(TEXT) / 2);
        /**
         * 将文本的对齐方式设置为CENTER后就相当于告诉android,我们这个文本绘制的时候从文本的中点向两端绘制
         * 如果对齐方式设置为LEFT，就相当于从文本的左端开始往右绘制，如果为RIGHT则从文本的右端开始往左绘制
         */
        mPaint.setTextAlign(Paint.Align.CENTER);
        baseX = (int) (canvas.getWidth() / 2);
        //计算baseline绘制的起始y轴坐标
        baseY = (int) (canvas.getHeight() / 2 + (Math.abs(mFontMetrics.ascent) + mFontMetrics.descent) / 2 - mFontMetrics.descent);

        canvas.drawText(TEXT, baseX, baseY, mPaint);
        // 为了便于理解我们在画布中心处绘制一条中线
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, mLinePaint);
    }
}
