package com.aliao.cvtraining.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.callback.CallBackClass;
import com.aliao.cvtraining.callback.Controller;
import com.aliao.cvtraining.callback.ICallBack;
import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/9/7.
 */
public class SimpleTouchEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_touch_event);

        initInstances();

//        Controller controller = new Controller(new ICallBack() {
//            @Override
//            public void run() {
//                L.d("实现在这里哦！");
//            }
//        });
//
//        controller.begin();
//        Controller controller1 = new Controller(new CallBackClass());
//        controller1.begin();
//        ICallBack callBack = new CallBackClass();
//        Controller controller2 = new Controller(callBack);
//        controller2.begin();
    }

    private void initInstances() {

//        findViewById(R.id.tv_touch).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SimpleTouchEventActivity.this, "Chilc事件触发了", Toast.LENGTH_SHORT).show();
//            }
//        });
//

        TextView touchTv = (TextView) findViewById(R.id.tv_touch);
        touchTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(SimpleTouchEventActivity.this, "touch", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
//        touchTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SimpleTouchEventActivity.this, "click", Toast.LENGTH_SHORT).show();
//            }
//        });
        Button touchBtn = (Button) findViewById(R.id.btn_touch);

        findViewById(R.id.btn_touch).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });

        touchBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(SimpleTouchEventActivity.this, "长按", Toast.LENGTH_SHORT).show();
                L.d("长按");
                return false;
            }
        });
//        touchBtn.setHapticFeedbackEnabled(true);
//        touchBtn.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
//        touchBtn.playSoundEffect(SoundEffectConstants.CLICK);

        findViewById(R.id.btn_touch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleTouchEventActivity.this, "点击", Toast.LENGTH_SHORT).show();
                L.d("点击");

            }
        });
    }
}
