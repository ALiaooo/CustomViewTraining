package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.ViewLogUtil;

/**
 * Created by 丽双 on 2015/9/7.
 */
public class TouchButton extends Button {
    public TouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                event.getActionMasked();
                int activePointerId = event.getActionIndex();

                ViewLogUtil.touchLog("Button", "dispatchTouchEvent", "ACTION_DOWN" + "[ x = " + x +", y = " + y + ", activePointerId = " + activePointerId + " ]");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("Button", "dispatchTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("Button", "dispatchTouchEvent", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("Button", "dispatchTouchEvent", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("Button", "dispatchTouchEvent", "ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                ViewLogUtil.touchLog("Button", "dispatchTouchEvent", "cancel");
                break;

        }

        boolean result = super.dispatchTouchEvent(event);
        ViewLogUtil.touchLog("Button", "dispatchTouchEvent", "return " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ViewLogUtil.touchLog("Button", "onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("Button", "onTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("Button", "onTouchEvent", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("Button", "onTouchEvent", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("Button", "onTouchEvent", "ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                ViewLogUtil.touchLog("Button", "onTouchEvent", "cancel");
                break;

        }
        return super.onTouchEvent(event);
    }
}
