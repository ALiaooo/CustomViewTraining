package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/8/18.
 */
public class CustomLinearLayout extends LinearLayout {
    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
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
