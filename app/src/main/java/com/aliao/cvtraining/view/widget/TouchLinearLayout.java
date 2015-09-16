package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ZoomButton;
import android.widget.ZoomControls;

import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.utils.ViewLogUtil;

/**
 * Created by 丽双 on 2015/9/11.
 */
public class TouchLinearLayout extends LinearLayout {
    public TouchLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
//        L.d("isChildrenDrawingOrderEnabled = " + isChildrenDrawingOrderEnabled() + ", order = " + getChildDrawingOrder(2, 1));

        L.d("LinearLayout getChildCount = "+getChildCount());
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
                ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "down"+ "[ x = "+x+", y = "+y+", activePointerId = "+activePointerId+" ]");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "move");
                break;//return super.dispatchTouchEvent(event);
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "up");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "cancel");
                break;

        }

        boolean result = super.dispatchTouchEvent(event);
        ViewLogUtil.touchLog("LinearLayout", "dispatchTouchEvent", "return " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ViewLogUtil.touchLog("LinearLayout", "onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("LinearLayout", "onTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("LinearLayout", "onTouchEvent", "ACTION_MOVE");
                break;//return super.onTouchEvent(event);
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("LinearLayout", "onTouchEvent", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("LinearLayout", "onTouchEvent", "ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                ViewLogUtil.touchLog("LinearLayout", "onTouchEvent", "cancel");
                break;

        }

        boolean result = super.onTouchEvent(event);

        ViewLogUtil.touchLog("LinearLayout", "onTouchEvent", "return " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
/*

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ViewLogUtil.touchLog("LinearLayout", "onInterceptTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ViewLogUtil.touchLog("LinearLayout", "onInterceptTouchEvent", "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                ViewLogUtil.touchLog("LinearLayout", "onInterceptTouchEvent", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                ViewLogUtil.touchLog("LinearLayout", "onInterceptTouchEvent", "ACTION_UP");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ViewLogUtil.touchLog("LinearLayout", "onInterceptTouchEvent", "ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                ViewLogUtil.touchLog("LinearLayout", "onInterceptTouchEvent", "cancel");
                break;

        }
*/

        boolean result = super.onInterceptTouchEvent(event);

//        ViewLogUtil.touchLog("LinearLayout", "onInterceptTouchEvent", "return " + result);
        return result;
    }
}
