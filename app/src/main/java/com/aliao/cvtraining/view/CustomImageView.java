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
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Spinner;

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
    private int mWidth;
    private int mHeight;


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
            Log.d("fuckt", "index = "+index);
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

        mRect = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("fuckt", "文字 mBound.width() = " + mBound.width() + ",文字 mBound.height()=" + mBound.height());
        Log.d("fuckt", "getWidth() = " + getWidth() + ", getHeight()=" + getHeight());
        Log.d("fuckt", "getMeasuredWidth() = " + getMeasuredWidth() + ", getMeasuredHeight()=" + getMeasuredHeight());

//        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() , mPaint);

        mPaint.setColor(mTitleColor);
        mPaint.setStyle(Paint.Style.FILL);
        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if (mBound.width() > mWidth)
        {

            Log.d("fuckt", "==========字体需要的宽度 > 当前设置的宽度===========" );
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            Log.d("fuckt", "msg = "+msg );
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else
        {
            //正常情况，将字体居中
            canvas.drawText(mTitleText, mWidth / 2 - mBound.width() / 2, mHeight - getPaddingBottom(), mPaint);
        }

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();//mImage.getWidth() + getPaddingLeft();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom() - mBound.height();//getPaddingTop() + mImage.getHeight();

        //画图
        if ((mWidth - getPaddingLeft() - getPaddingRight()) > mImage.getWidth()){//图片决定宽，设定的宽大于图片的宽——拉伸
            mRect.right = getPaddingLeft() +  mImage.getWidth();
        }

        if ((mHeight - getPaddingTop() -getPaddingBottom() - mBound.height()) > mImage.getHeight()){//设定的高大于图片的高——拉伸
            mRect.bottom = getPaddingTop() + mImage.getHeight();
        }

        if (mImageScale == IMAGE_SCALE_FITXY){
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }else {

            Log.d("fuckt", "-------------------->mImage.getWidth() = "+mImage.getWidth());
            //居中显示
            mRect.left = mWidth / 2 - mImage.getWidth() / 2;
            mRect.right = mRect.left + mImage.getWidth();
            mRect.top = (mHeight - mBound.height()) / 2 - mImage.getHeight() / 2;
            mRect.bottom = (mHeight - mBound.height()) / 2 + mImage.getHeight() / 2;
            Log.d("fuckt", "-------------------->mRect.left = "+mRect.left + ", mRect.right="+mRect.right);
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
        Log.d("fuckt", " widthSize = " + widthSize + ", heightSize =" + heightSize);
        Log.d("fuckt", " widthMode = " + widthMode + ", heightMode =" + heightMode);


        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }else {
            //由图片决定的宽
            int widthByImage = getPaddingLeft() + mImage.getWidth() +getPaddingRight();
            //由字体决定的宽
            int widthByText =  getPaddingLeft() + mBound.width() +getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST){
                int desire = Math.max(widthByImage, widthByText);
                Log.d("fuckt", "宽 -->  决定宽度widthDesire = " + desire + ", widthSize =" + widthSize);
                mWidth = Math.min(desire, widthSize);
            }
//            mWidth = widthByText;
        }

        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else {
            int desire = getPaddingTop() + mBound.height() + mImage.getHeight() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST){
                Log.d("fuckt", "高 -->  决定高度heightDesire = " + desire + ", heightSize =" + heightSize);
                mHeight = Math.min(desire, heightSize);
            }
        }

        Log.d("fuckt", "最终显示测量的宽mWidth = " + mWidth + ", 高mHeight =" + mHeight);

        setMeasuredDimension(mWidth, mHeight);
    }
}
