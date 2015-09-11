package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.MeasureSpecUtil;

/**
 * Created by 丽双 on 2015/8/18.
 */
public class CustomTextView extends TextView {
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        MeasureSpecUtil.printMeasureSpec("TextView", widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("[textview] dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                L.d("[textview] dispatchTouchEvent ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("-->[textview] dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("---->[textview] dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                L.d("[textview] dispatchTouchEvent ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                L.d("[textview] dispatchTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        boolean onDispatchEvent = super.dispatchTouchEvent(event);
        L.d("Textview super.dispatchTouchEvent(event) = "+onDispatchEvent);
        return onDispatchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("[textview] onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                L.d("[textview] onTouchEvent ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("-->[textview] onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("---->[textview] onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                L.d("[textview] onTouchEvent ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                L.d("[textview] onTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        boolean onTouchEvent = super.onTouchEvent(event);
        L.d("Textview super.onTouchEvent(event) = "+onTouchEvent);
        return onTouchEvent;
    }
}
