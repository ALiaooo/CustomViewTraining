package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/8/19.
 */
public class CustomListview extends ListView {
    public CustomListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        L.d("heightMode = "+heightMode+", heightSize = "+heightSize+", widthMode = "+widthMode+", widthSize = "+widthSize);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Listview -- dispatchTouchEvent -- ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                L.d("Listview -- dispatchTouchEvent -- ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                L.d("Listview -- dispatchTouchEvent -- ACTION_UP");
                break;
        }

        boolean dispatch = super.dispatchTouchEvent(ev);
        L.d("Listview return dispatchTouchEvent = "+dispatch);
        return dispatch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Listview -- onTouchEvent -- ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                L.d("Listview -- onTouchEvent -- ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                L.d("Listview -- onTouchEvent -- ACTION_UP");
                break;
        }

        boolean onTouchEvent = super.onTouchEvent(ev);
        L.d("Listview return onTouchEvent = "+onTouchEvent);
        return onTouchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Listview -- onInterceptTouchEvent -- ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                L.d("Listview -- onInterceptTouchEvent -- ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                L.d("Listview -- onInterceptTouchEvent -- ACTION_UP");
                break;
        }

        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(ev);
        L.d("Listview return onInterceptTouchEvent = " + onInterceptTouchEvent);
        return onInterceptTouchEvent;
    }

}
