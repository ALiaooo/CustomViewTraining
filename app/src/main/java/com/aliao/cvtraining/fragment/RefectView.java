package com.aliao.cvtraining.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.aliao.cvtraining.R;

/**
 * Created by 丽双 on 2015/4/16.
 */
public class RefectView extends View {
    private Paint mPaint;
    private Bitmap mSrcBitmap;
    public RefectView(Context context) {
        super(context);
    }

    public RefectView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initRes(context);
        /**
         * 初始化画笔
         */
        initPaint();
    }

    /**
     * 初始化资源
     * @param context
     */
    private void initRes(Context context) {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gril);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //黑色背景
        canvas.drawColor(Color.BLACK);

    }
}
