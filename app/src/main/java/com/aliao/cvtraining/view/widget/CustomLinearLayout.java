package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.MeasureSpecUtil;

/**
 * Created by 丽双 on 2015/8/18.
 */
public class CustomLinearLayout extends LinearLayout {
    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        L.d("---------------onMeasure LinearLayout----------------");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        L.d("heightMode = " + heightMode + ", heightSize = " + heightSize + ", widthMode = " + widthMode + ", widthSize = " + widthSize);
        MeasureSpecUtil.printMode(heightMode, "height");
        MeasureSpecUtil.printMode(widthMode, "width");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public static int getChildMeasureSpec(int parentHeightSpec, int padding, int childHeight){//childHeight = lp.height

        int parentHeightSpecMode = MeasureSpec.getMode(parentHeightSpec);
        int parentHeightSpecSize = MeasureSpec.getSize(parentHeightSpec);

        int size = Math.max(0, parentHeightSpecSize - padding);

        int resultSize = 0;
        int resultMode = 0;

        switch(parentHeightSpecMode ){
            case MeasureSpec.EXACTLY:

                /**
                 * child有精确值，那就由child自己决定
                 * child如果设置match_parent，就用parent的可用最大值（一定填充到最大值）
                 * child如果设置wrap_content，就用parent的可用最大值（但不一定用得到最大的值）
                 */
                resultSize = parentHeightSpecSize - padding;

                if (childHeight >= 0){//有精确值
                    //hild有精确值，那就由child自己决定
                    resultSize = childHeight;
                    resultMode = MeasureSpec.EXACTLY;

                }else if (childHeight == ViewGroup.LayoutParams.MATCH_PARENT){
                    resultSize = size;
                    resultMode = MeasureSpec.EXACTLY;
                }else if (childHeight == ViewGroup.LayoutParams.WRAP_CONTENT){
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                }
                break;

            case MeasureSpec.AT_MOST:
                if (childHeight > 0){
                    //如果parent没有设置精确值，而child有精确值，就尊重child的设置，即使parent的高度比child设置的高度要小。
                    resultSize = childHeight;
                    resultMode = MeasureSpec.EXACTLY;
                }else if (childHeight == ViewGroup.LayoutParams.MATCH_PARENT){
                    //这个条件存在一定矛盾，parent的最终高度依赖于child的实际高度，但是child的高度设定又是填充parent的高度，所以最后实际显示的是child的实际高度
                    //可能调用多次，第一次显示parent的高度，第二次显示实际占用的高度。
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;//最终变为EXACTLY
                }else if (childHeight == ViewGroup.LayoutParams.WRAP_CONTENT){
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                }
                break;

            case MeasureSpec.UNSPECIFIED:
//                if (childHeight >= 0){
//                    resultSize = childHeight;
//                    resultMode = MeasureSpec.EXACTLY;
//                }else if (childHeight == ViewGroup.LayoutParams.MATCH_PARENT){
//                    resultSize = View.sUseZeroUnspecifiedMeasureSpec ? 0 : size;
//                    resultMode = MeasureSpec.UNSPECIFIED;
//                }else if (childHeight == ViewGroup.LayoutParams.WRAP_CONTENT){
//                    resultSize = View.sUseZeroUnspecifiedMeasureSpec ? 0 : size;
//                    resultMode = MeasureSpec.UNSPECIFIED;
//                }
                break;
        }

        return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Linearlayout dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("Linearlayout dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("Linearlayout dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean onDispatchEvent = super.dispatchTouchEvent(event);
        L.d("LinearLayout super.dispatchTouchEvent(event) = "+onDispatchEvent);
        return onDispatchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Linearlayout onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("Linearlayout onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("Linearlayout onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean onTouchEvent = super.onTouchEvent(event);
        L.d("LinearLayout super.onTouchEvent(event) = "+onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Linearlayout onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("Linearlayout onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("Linearlayout onInterceptTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean onInterceptTouch = super.onInterceptTouchEvent(ev);
        L.d("super.onInterceptTouchEvent(ev) = "+onInterceptTouch);
        return onInterceptTouch;
    }
}
