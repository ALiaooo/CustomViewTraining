package com.aliao.cvtraining.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.aliao.cvtraining.R;

/**
 * Created by 丽双 on 2015/4/8.
 */
public class CustomImageView extends View {

    private static final int IMAGE_SCALE_FITXY = 0;

    private String mTitleText;
    private int mTitleSize;
    private int mTitleColor;
    private int mImageScale;
    private Bitmap mImage;

    private Rect mBound;
    private Paint mPaint;
    private Rect mRect;


    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);

        for (int i = 0; i <a.getIndexCount(); i++){
            int index = a.getIndex(i);
            Log.d("fuck", "index = "+index);
            switch (index){
                case R.styleable.CustomImageView_introText:
                    mTitleText = a.getString(index);
                    break;
                case R.styleable.CustomImageView_introTextSize:
                    mTitleSize = a.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomImageView_introTextColor:
                    mTitleColor = a.getColor(index, Color.GREEN);
                    break;
                case R.styleable.CustomImageView_image:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(index, R.mipmap.ic_launcher));
                    break;
                case R.styleable.CustomImageView_imageScaleType:
                    mImageScale = a.getInt(index, 0);
                    break;
            }
        }

        a.recycle();

        mPaint = new Paint();
        mPaint.setColor(mTitleColor);
        mPaint.setTextSize(mTitleSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        mRect = new Rect(0, 0, 100, 100);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("fuck", " mRect.width() = " + mBound.width() + ", mRect.height()=" + mBound.height());
        Log.d("fuck", "getWidth() = " + getWidth() + ", getHeight()=" + getHeight());
        Log.d("fuck", "getMeasuredWidth() = " + getMeasuredWidth() + ", getMeasuredHeight()=" + getMeasuredHeight());

        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);

        if (mImageScale == IMAGE_SCALE_FITXY){
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.d("fuck", " widthSize = " + widthSize + ", heightSize =" + heightSize);
        Log.d("fuck", " widthMode = " + widthMode + ", heightMode =" + heightMode);
        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            width = getPaddingLeft() + mBound.width() +getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            height = getPaddingTop() + mBound.height() + getPaddingBottom();
        }

        setMeasuredDimension(width, height);
    }
}
