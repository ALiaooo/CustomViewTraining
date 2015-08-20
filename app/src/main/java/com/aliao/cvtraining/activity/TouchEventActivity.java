package com.aliao.cvtraining.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.view.widget.CustomButton;

/**
 * Created by 丽双 on 2015/8/18.
 *
 * http://ryantang.me/blog/2014/01/02/android-event-dispatch/
 *
 * http://hukai.me/android-deeper-touch-event-dispatch-process/
 */
public class TouchEventActivity extends Activity implements View.OnClickListener, View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        CustomButton button = (CustomButton) findViewById(R.id.btn_custom);
        button.setOnClickListener(this);
        button.setOnTouchListener(this);
        CustomButton innerBtn = (CustomButton) findViewById(R.id.btn_inner);
        innerBtn.setOnClickListener(this);
        innerBtn.setOnTouchListener(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.custom_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.d("Layout onClick");
            }
        });
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        L.d("layout onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        L.d("layout onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        L.d("layout onTouch ACTION_UP");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Activity dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("Activity dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("Activity dispatchTouchEvent ACTION_UP");
                break;
            default:

                break;
        }

        boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        L.d("【Activity】 super.dispatchTouchEvent(ev) = " + dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        L.d("onUserInteraction");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Activity onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("Activity onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("Activity onTouchEvent ACTION_UP");
                break;
            default:
                break;
        }
        boolean touchEvent = super.onTouchEvent(event);
        L.d("【Activity】 super.onTouchEvent(event) = "+touchEvent);
        return touchEvent;
    }

    @Override
    public void onClick(View v) {
        L.d("Activity Button onClick!");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.d("Button onTouch ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                L.d("Button onTouch ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                L.d("Button onTouch ACTION_UP");
                break;
            default:
                break;
        }
        return false;
    }

    public void onScrollViewpager(View view){
        startActivity(new Intent(TouchEventActivity.this, ScrollViewPagerActivity.class));
    }

    public void onViewDrawingProcedure(View view){
        startActivity(new Intent(TouchEventActivity.this, ViewDrawingProcedureActivity.class));
    }
}
