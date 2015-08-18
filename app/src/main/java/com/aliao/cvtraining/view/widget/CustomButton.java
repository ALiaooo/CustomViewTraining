package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/8/18.
 */
public class CustomButton extends Button {


    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("button dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("button dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("button dispatchTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean onDispatchEvent = super.dispatchTouchEvent(event);
        L.d("[button] super.dispatchTouchEvent(event) = "+onDispatchEvent);
        return onDispatchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("button onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("button onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("button onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean onTouchEvent = super.onTouchEvent(event);
        L.d("[button] super.onTouchEvent(event) = "+onTouchEvent);
        return onTouchEvent;
    }
}
