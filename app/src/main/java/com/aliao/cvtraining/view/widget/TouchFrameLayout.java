package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.ViewLogUtil;

/**
 * Created by 丽双 on 2015/9/16.
 */
public class TouchFrameLayout extends FrameLayout {
    public TouchFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        L.d("FrameLayout getChildCount = "+getChildCount());
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        L.d("FrameLayout getChildCount = "+getChildCount());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                event.getActionMasked();
                int activePointerId = event.getActionIndex();
                ViewLogUtil.touchLog("FrameLayout", "dispatchTouchEvent", "down"+ "[ x = "+x+", y = "+y+", activePointerId = "+activePointerId+" ]");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("FrameLayout", "dispatchTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("FrameLayout", "dispatchTouchEvent", "move");
                break;//return super.dispatchTouchEvent(event);
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("FrameLayout", "dispatchTouchEvent", "up");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                ViewLogUtil.touchLog("FrameLayout", "dispatchTouchEvent", "cancel");
                break;
        }

        boolean result = super.dispatchTouchEvent(event);
        ViewLogUtil.touchLog("FrameLayout", "dispatchTouchEvent", "return " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ViewLogUtil.touchLog("FrameLayout", "onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("FrameLayout", "onTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("FrameLayout", "onTouchEvent", "ACTION_MOVE");
                break;//return super.onTouchEvent(event);
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("FrameLayout", "onTouchEvent", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("FrameLayout", "onTouchEvent", "ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                ViewLogUtil.touchLog("FrameLayout", "onTouchEvent", "cancel");
                break;

        }

        boolean result = super.onTouchEvent(event);

        ViewLogUtil.touchLog("FrameLayout", "onTouchEvent", "return " + result);
        return result;
    }
}
