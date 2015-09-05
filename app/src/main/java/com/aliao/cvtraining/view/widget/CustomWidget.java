package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by liaolishuang on 15/9/2.
 */
public class CustomWidget extends TextView {

    public CustomWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * @hide
     */
    int mOldWidthMeasureSpec = Integer.MIN_VALUE;
    /**
     * @hide
     */
    int mOldHeightMeasureSpec = Integer.MIN_VALUE;


    private LongSparseLongArray mMeasureCache;


    /**
     * 首先要了解，参数代表的意义
     * widthMeasureSpec是parent对当前child宽度的推荐的尺寸规格，也可以说是限制大小。第一次看到这个参数名字的时
     * 候，觉得名字很生硬，其实在了解了该参数的来源和意义之后对他的命名就很好理解。宽度是宽度，尺寸规格包含了宽度，
     * 还包含了该宽度的模式mode，尺寸规格即源码中的MeasureSpec由两部分组成：尺寸大小和尺寸模式。而
     * widthMeasureSpec是由宽度大小(size)和宽度模式(mode)组成。是由MeasureSpec.makeMeasureSpec(int size, int mode)
     * 方法得到的，所以哦，在view测量中看到spec后缀的话就表示包含了size和mode，要了解MeasureSpec类的概念。
     * 除此以外，还要知道该宽度尺寸规格的确定是由parent和child共同决定的,就是和他们彼此的宽度尺寸有关系。这个规则
     * 的制定在ViewGroup的getChildMeasureSpec(int,int,int)方法中.这个是必须要清楚的点，否则就无法正确理解参数的
     * 意义，看后面的代码理解起来也会变得有些吃力，而且很容易混淆。
     * heightMeasureSpec和widthMeasureSpec的来源都是一样的。
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    public final void measures(int widthMeasureSpec, int heightMeasureSpec){

        //判断是否是视觉边界布局
        boolean optical = isLayoutModeOptical(this);

        if(optical != isLayoutModeOptical(mParent)){
            Insets insets = getOpticalInsets();
            int oWidth = insets.left + insets.right;
            int oHeight = insets.top + insets.bottom;
            //重新调整尺寸规格
            widthMeasureSpec = MeasureSpec.adjust(widthMeasureSpec, optical ? -oWidth : oWidth);
            heightMeasureSpec = MeasureSpec.adjust(heightMeasureSpec, optical ? -oHeight : oHeight);
        }

        /**
         * 这个key保存了widhtMeasureSpec和heightMeasureSpec。前32位是widhtMeasureSpec，后32位是heightMeasureSpec。
         */
        long key = widthMeasureSpec << 32 | heightMeasureSpec & 0xffffffffL;
        if(mMeasureCache == null) mMeasureCache = new LongSparseLongArray(2);

        /**
         * 问题：第一个条件是在哪里设置的，表示什么意思？进入Layout阶段？如果当前view的宽高尺寸有变化的条件下进入
         */
        if((mPrivateFlags & PFLAG_FORCE_LAYOUT) == PFLAG_FORCE_LAYOUT ||
                widthMeasureSpec != mOldWidthMeasureSpec ||
                heightMeasureSpec != mOldHeightMeasureSpec){
            /**
             * 清除掉测量维度的flag（measured dimension flag）,这个flag是在view的onMeasure()方法结束后设置的。
             * 还记得setMeasuredDimension(int,int)方法吗用来保存测量的宽和高。最终会调用到
             * setMeasuredDimensionRaw(measuredWidth, measuredHeight),其中执行了
             * mPrivateFlags |= PFLAG_MEASURED_DIMENSION_SET;
             */
            mPrivateFlags &= ~PFLAG_MEASURED_DIMENSION_SET;
            //4.2的一个新特性，支持从右到左的布局。主要是方便开发者去支持阿拉伯语/波斯语等阅读习惯是从右往左的
            resolveRtlPropertiesIfNeeded();

            int cacheIndex = (mPrivateFlags & PFLAG_FORCE_LAYOUT) == PFLAG_FORCE_LAYOUT ? -1 :
                    mMeasureCache.indexOfKey(key);

            if (cacheIndex < 0 || sIgnoreMeasureCache){
                // measure ourselves, this should set the measured dimension flag back
                onMeasure(widthMeasureSpec, heightMeasureSpec);
                /**
                 * 合格mPrivateFlags3又是啥?前面有了一个mPrivateFlags了已经...
                 * 这个变量有什么作用？
                 * 清空这个flag
                 */
                mPrivateFlags3 &= ~PFLAG_MEASURE_NEEDED_BEFORE_LAYOUT;
            }else{
                /**
                 * 如果缓存中已经存在了这个尺寸规格的测量结果，那么直接从缓存中取来用，不需要重新测量了。
                 */
                long value = mMeasureCache.valueAt(cacheIndex);
                //保存
                setMeasuredDimensionRaw((int) (value >> 32), (int) value);
                //添加该flag
                mPrivateFlags3 |= PFLAG_MEASURE_NEEDED_BEFORE_LAYOUT;
            }

            //如果在onMwasure()方法中没有设置setMeasuredDimension()方法，则抛出异常
            // flag not set, setMeasuredDimension() was not invoked, we raise
            // an exception to warn the developer
            if ((mPrivateFlags & PFLAG_MEASURED_DIMENSION_SET) != PFLAG_MEASURED_DIMENSION_SET) {
                throw new IllegalStateException("View with id " + getId() + ": "
                        + getClass().getName() + "#onMeasure() did not set the"
                        + " measured dimension by calling"
                        + " setMeasuredDimension()");
            }

            mPrivateFlags |= PFLAG_LAYOUT_REQUIRED;

        }

        mOldWidthMeasureSpec = widthMeasureSpec;
        mOldHeightMeasureSpec = heightMeasureSpec;

        //把每次的测量结果都存到缓存中
        mMeasureCache.put(key,
                ((long) mMeasuredWidth) << 32 | (long) mMeasuredHeight & 0xffffffffL); // suppress sign extension


    }

    /**
     * 只有ViewGroup里才有opticalLayoutMode
     * @param o
     * @return
     */
    public static boolean isLayoutModeOptical(Object o){
        return o instanceof ViewGroup && ((ViewGroup) o).isLayoutModeOptical();
    }


    private TextDirectionHeuristic mTextDir;

    private static final BoringLayout.Metrics UNKNOW_BORING = new BoringLayout.Metrics();

    private Layout mLayout;

    private TextUtils.TruncateAt mEllipsize;//折叠

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获取到parent建议的宽和高的尺寸和模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        /**
         * 定义TextView的最终测量宽度和高度
         */
        int width;
        int height;

        /**
         * 在该方法前已经定义了UNKONW_BORING变量：
         * BoringLayout.Metrics UNKNOW_BORING = new BoringLayout.Metrics();
         * BoringLayout是什么呢
         */
        BoringLayout.Metrics boring = UNKNOW_BORING;
        BoringLayout.Metrics hintBoring = UNKNOW_BORING;

        /**
         * mTextDir的定义：TextDirectionHeuristic mTextDir;
         */
        if(mTextDir == null){
            mTextDir = getTextDrectionHeuristic();
        }

        /**
         * 还不知道这两个变量是干嘛的
         */
        int des = -1;
        boolean formexisting = false;

        /**
         * 计算宽度width
         * 当推荐宽度模式时EXACTLY时，说明child设置了精确地高度，只要设置了精确地值，那么测量宽度即为精确值。
         */
        if (widthMode == MeasureSpec.EXACTLY){
            //parent has told us how big to be. So be it.
            width = widthSize;
        }else {
            /**
             *
             * mLayout的来源？
             *
             * mEllipsize的定义：TextUtil.TruncateAt

             public enum TruncateAt {//表示从哪儿开始截短...
                START,
                MIDDLE,
                END,
                MARQUEE,
                END_SMALL
             }

             来源：
             TextView的构造函数中
             case com.android.internal.R.styleable.TextView_ellipsize:
                ellipsize = a.getInt(attr, ellipsize);
             switch(ellipsize){
                case 1:
                    setEllipsize(TextUtils.TruncateAt.END);
                    break;
             }

             public void setEllipsize(TextUtils.TruncateAt where){
                if(mEllipsize != where){
                    mEllipsize = where;
                    ...
                }
             }

             mEllipsize

             */
            if(mLayout != null && mEllipsize == null){
                /**
                 * private static int desired(Layout layout){
                 *     //getLineCount返回该布局中text的行数，是一个抽象方法，谁实现了他呢？
                 *     int n = layout.getLineCount();
                 *     CharSequence text = layout.getText();
                 *     float max = 0;
                 *     for(int i = 0; i < n - 1; i++){
                 *         if(text.charAt(layout.getLineEnd(i) - 1) != '\n'){
                 *              return -1;
                 *         }
                 *     }
                 *     //取所有行中宽度最大的值
                 *     for(int i = 0; i < n; i++){
                 *          max = Math.max(max, layout.getLineWidth(i));
                 *     }
                 *
                 *     return (int) Math.ceil(max);//向上取整
                 * }
                 */
                des = desired(mLayout);
            }

            if(des < 0){
                //boring = BoringLayout.isBoring();
            }
        }

        /**
         * 计算高度height
         */
        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {

        }
    }
}
