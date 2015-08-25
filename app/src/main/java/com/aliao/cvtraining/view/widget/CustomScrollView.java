package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/8/19.
 */
public class CustomScrollView extends ScrollView {
    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        L.d("---------------onMeasure CustomScrollView----------------");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        L.d("heightMode = " + heightMode + ", heightSize = " + heightSize + ", widthMode = " + widthMode + ", widthSize = " + widthSize);
        printMode(heightMode, "height");
        printMode(widthMode, "width");
        L.d("measure before : getMeasuredHeight = " + getMeasuredHeight() + ", getMeasuredWidth = " + getMeasuredWidth() + ",getChildCount() = "+getChildCount());



        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        L.d("measure after : getMeasuredHeight = " + getMeasuredHeight() + ", getMeasuredWidth = " + getMeasuredWidth() + ",getChildCount() = "+getChildCount());
    }


    private void printMode(int mode, String tag){
        if (mode == MeasureSpec.AT_MOST){
            L.d(tag+" mode  = AT_MOST");
        }else if (mode == MeasureSpec.EXACTLY){
            L.d(tag+" mode  = EXACTLY");
        }else if (mode == MeasureSpec.UNSPECIFIED){
            L.d(tag+" mode  = UNSPECIFIED");
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Scrollview -- dispatchTouchEvent -- ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                L.d("Scrollview -- dispatchTouchEvent -- ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                L.d("Scrollview -- dispatchTouchEvent -- ACTION_UP");
                break;
        }

        boolean dispatch = super.dispatchTouchEvent(ev);
        L.d("Scrollview return dispatchTouchEvent = "+dispatch);
        return dispatch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Scrollview -- onTouchEvent -- ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                L.d("Scrollview -- onTouchEvent -- ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                L.d("Scrollview -- onTouchEvent -- ACTION_UP");
                break;
        }

        boolean onTouchEvent = super.onTouchEvent(ev);
        L.d("Scrollview return onTouchEvent = "+onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Scrollview -- onInterceptTouchEvent -- ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                L.d("Scrollview -- onInterceptTouchEvent -- ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                L.d("Scrollview -- onInterceptTouchEvent -- ACTION_UP");
                break;
        }

        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(ev);
        L.d("Scrollview return onInterceptTouchEvent = "+onInterceptTouchEvent);
        return onInterceptTouchEvent;
    }
}
