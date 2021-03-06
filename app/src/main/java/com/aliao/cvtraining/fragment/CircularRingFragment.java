package com.aliao.cvtraining.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.view.CustomCircularRingView;

/**
 * Created by 丽双 on 2015/4/10.
 */
public class CircularRingFragment extends Fragment {
    private LinearLayout mLayoutContainer;
    private CustomCircularRingView mCircularView;
    private int mRadiu;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circular_ring, container, false);
        initViews(view);
        return view;

    }

    private void initViews(View view) {
        /**
         * 1.java code动态创建自定义view时调用C1（第一个构造函数）
         * 2.通过布局文件xml创建一个view，xml里设定的属性会通过AttributeSet参数传递给构造函数，所以此时调用的是C2（第二个构造函数）
         * 3.
         */
//        mLayoutContainer = (LinearLayout) findViewById(R.id.layout_aige_container);
//        mLayoutContainer.addView(new CustomCircularRingView(this));

        /**
         * 设置圆环半径,重绘view使用invalidate()
         */
//        mCircularView = (CustomCircularRingView) findViewById(R.id.custom_circular_view);
//        mCircularView.setRadiu(200);

        /**
         * 开线程定时间段地设置圆环半径
         */
//        mCircularView = (CustomCircularRingView) findViewById(R.id.custom_circular_view);
//        new Thread(){
//            @Override
//            public void run() {
//
//                /**
//                 * 确保线程不断执行不断刷新界面
//                 */
//                while (true){
//
//                    if(mRadiu <= 200){
//                        mRadiu += 10;
//
//                        handler.obtainMessage().sendToTarget();
//
//                    }else {
//                        mRadiu = 0;
//                    }
//
//                    try {
//                        sleep(40);//执行一次暂停40毫秒
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }.start();

        /**
         * 将圆环的动态效果的逻辑处理代码放到自定义View中去处理，让CustomCircularRingView实现Runnable接口
         * 注：非UI线程中刷新view使用postInvalidate();
         */
        mCircularView = (CustomCircularRingView) view.findViewById(R.id.custom_circular_view);
        new Thread(mCircularView).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            mCircularView.setRadiu(mRadiu);//在主线程中刷新view
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(null != handler)
            handler.removeCallbacksAndMessages(null);
    }

}
