package com.aliao.cvtraining.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 丽双 on 2015/8/25.
 * https://sriramramani.wordpress.com/2015/05/06/custom-viewgroups/
 */
public class ProfilePhotoLayout extends ViewGroup {

    private ImageView mProfilePhoto;
    private TextView mMenu;
    private TextView mTitle;
    private TextView mSubtitle;

    public ProfilePhotoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1.Setup initial constraints
        int widthConstraints = getPaddingLeft() + getPaddingRight();//÷title外的剩余空间
        int heightConstraints = getPaddingTop() + getPaddingBottom();
        int width = 0;//最大child的宽，child占据的最大宽
        int height = 0;//child占据的最大高

        //2.Measure the ProfilePhoto
        measureChildWithMargins(mProfilePhoto, widthMeasureSpec, widthConstraints, heightMeasureSpec, heightConstraints);

        //3.Update the constraints
        widthConstraints += mProfilePhoto.getMeasuredWidth();
        width += mProfilePhoto.getMeasuredWidth();
        height = Math.max(mProfilePhoto.getMeasuredHeight(), height);

        //4.Measure the menu
        measureChildWithMargins(mMenu, widthMeasureSpec, widthConstraints, heightMeasureSpec, heightConstraints);

        //5.Update the constraints
        widthConstraints += mMenu.getMeasuredWidth();
        width += mMenu.getMeasuredWidth();
        height = Math.max(mMenu.getMeasuredHeight(), height);

        //6.Prepare the vertical MeasureSpec
        int verticalWidthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec) - widthConstraints,MeasureSpec.getMode(widthMeasureSpec));
        int verticalHeightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec) - widthConstraints,MeasureSpec.getMode(heightMeasureSpec));

        //7.Measure the title
        measureChildWithMargins(mTitle, verticalWidthMeasureSpec, 0, verticalHeightMeasureSpec, 0);

        //8.Measure the subtitle
        measureChildWithMargins(mSubtitle, verticalWidthMeasureSpec, 0, verticalHeightMeasureSpec, 0);

        //9.Update the sizes
        width = widthConstraints + Math.max(mTitle.getMeasuredWidth(),mSubtitle.getMeasuredWidth());
        height = heightConstraints + Math.max(mTitle.getMeasuredHeight(),mSubtitle.getMeasuredHeight());

        //10.Set the dimmension for this ViewGroup
        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
