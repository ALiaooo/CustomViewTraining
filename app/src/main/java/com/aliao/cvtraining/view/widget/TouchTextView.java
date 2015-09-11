package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.ViewLogUtil;

/**
 * Created by 丽双 on 2015/9/6.
 */
public class TouchTextView extends TextView {

    public TouchTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int pointerCount = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                pointerCount = event.getPointerCount();
                float x = event.getX();
                float y = event.getY();

                event.getActionMasked();
                int activePointerId = event.getActionIndex();

                ViewLogUtil.touchLog("TextView", "dispatchTouchEvent", "ACTION_DOWN" + "[ x = " + x +", y = " + y + ", activePointerId = " + activePointerId + " ]");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                pointerCount = event.getPointerCount();
                ViewLogUtil.touchLog("TextView", "dispatchTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("TextView", "dispatchTouchEvent", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("TextView", "dispatchTouchEvent", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("TextView", "dispatchTouchEvent", "ACTION_POINTER_UP");
                break;

        }

        boolean result = super.dispatchTouchEvent(event);
        ViewLogUtil.touchLog("TextView", "dispatchTouchEvent", "return " + result);
        return result;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ViewLogUtil.touchLog("TextView", "onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("TextView", "onTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("TextView", "onTouchEvent", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("TextView", "onTouchEvent", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("TextView", "onTouchEvent", "ACTION_POINTER_UP");
                break;

        }

        boolean result = super.onTouchEvent(event);
        ViewLogUtil.touchLog("TextView", "onTouchEvent", "return " + result);
        return result;
    }



}
