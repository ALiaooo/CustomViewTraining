package com.aliao.cvtraining.view.refreshview;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/9/6.
 */
public class PullToRefreshView extends ViewGroup {

    private static final int DRAG_MAX_DISTANCE = 120;
    private static final float DRAG_RATE = .5f;
    private int mTotalDragDistance;
    private float mCurrentDragPercent;

    private View mTarget;//xml中包裹的view
    private ImageView mRefreshView;//隐藏在背后的刷新时显示的视图
    private int mCurrentOffsetTop;
    private boolean mIsBeingDragged;
    private int mActivePointerId;
    private float mInitialMotionY;

    public PullToRefreshView(Context context) {
        super(context, null);

    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTotalDragDistance = convertDpToPixel(context, DRAG_MAX_DISTANCE);

        mRefreshView = new ImageView(context);
        mRefreshView.setImageResource(R.mipmap.car);
        addView(mRefreshView);

        setWillNotDraw(false);
        //看看接下来有没有什么地方会重载getChildDrawingOrder(int childCount,int i)，如果没有那么children的绘制顺序就是默认的。
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
    }

    public int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 首先onMeasure中一定要调用setMeasuredDimmension(int, int)来保存自身的测量宽高
         * 第二，这里直接调用了super.onMeasure(int,int)说明，该ViewGroup直接使用parent要求的宽高。
         * 如果设置了精确值，就使用精确值
         * 如果设置了match_parent，就使用parent的可用最大高度
         * 如果设置wrap_parent，也是使用parent的可用最大高度。本来wrap_parent代表的意思是高度取所包裹
         * 内容的总高度，那么所有child累加的高度即为该viewgroup的高度，这些需要代码去计算。但是这里
         * 因为这届调用了surper.onMeasure()，且ViewGroup中没有单独处理，所以保存的宽高值就是parent的
         * 可用最大高度啦！
         *
         * 该ViewGroup的的测量高度不用我们操心，所以接下来去测量child的高度
         */
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        if (mTarget == null)
//            return;

        /**
         * 该ViewGroup里就只设置两个view：
         * 1.RefreshView
         * 2.mTarget，例如listview
         */
        int count = getChildCount();
        if (count > 0){
            for (int i = 0; i < count; i++){
                View child = getChildAt(i);

                if (child  == null){
                    continue;
                }

                if (child.getVisibility() == GONE){
                    continue;
                }
                //目前只有一个listview
                if (mRefreshView != child)
                    mTarget = child;
                measureChildWithMargins(child);
            }
        }
    }

    private void measureChildWithMargins(View child){
        //这里是要自己定义一个LayoutParams来继承MarginLayoutParams吧
//        final ViewGroup.LayoutParams lp =  child.getLayoutParams();

        int childWidthMeasureSpec = getChildMeasureSpec(getMeasuredWidth(),getPaddingLeft() + getPaddingRight() /*+ lp.leftMargin + lp.rightMargin*/);
        int childHeightMeasureSpec = getChildMeasureSpec(getMeasuredHeight(),getPaddingTop() + getPaddingBottom() /*+ lp.topMargin + lp.bottomMargin*/);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    private int getChildMeasureSpec(int size, int padding){
        int resultMode = MeasureSpec.EXACTLY;
        int resultSize = size - padding;
        return MeasureSpec.makeMeasureSpec(resultSize ,resultMode);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        L.d("mTarget = "+mTarget);
        int count = getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                //目前只有一个listview
                if (child != mRefreshView)
                    mTarget = child;
            }
        }


        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = width - getPaddingRight();
        int bottom = height - getPaddingBottom();

        mRefreshView.layout(left, top, right, bottom);

        if (mTarget == null){
            return;
        }
        mTarget.layout(left, top + mCurrentOffsetTop, right, bottom + mCurrentOffsetTop);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        switch (action){
            case MotionEvent.ACTION_DOWN:
                setTargetOffsetTop(0, true);
                break;
            case MotionEvent.ACTION_MOVE:
                setTargetOffsetTop(50,true);
                mIsBeingDragged = true;
                break;
            case MotionEvent.ACTION_UP:
                mIsBeingDragged = false;
                break;
        }

        return mIsBeingDragged;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = MotionEventCompat.getActionMasked(event);
        int downPosition = 0;
        switch (action){
            case MotionEvent.ACTION_DOWN:

                //获取第一个触控点的id
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                final int pointerIndex1 = MotionEventCompat.findPointerIndex(event, mActivePointerId);

                /**
                 * pointerIndex会有小于0的情况？
                 */
                if (pointerIndex1 < 0)
                    return false;

                mInitialMotionY = MotionEventCompat.getY(event, pointerIndex1);

                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                final int index = MotionEventCompat.getActionIndex(event);
                mActivePointerId = MotionEventCompat.getPointerId(event, index);
                break;
            case MotionEvent.ACTION_MOVE:
                final int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }

                final float y = MotionEventCompat.getY(event, pointerIndex);
                final float yDiff = y - mInitialMotionY;
                final float scrollTop = yDiff * DRAG_RATE;
                mCurrentDragPercent = scrollTop / mTotalDragDistance;
                if (mCurrentDragPercent < 0) {
                    return false;
                }
                float boundedDragPercent = Math.min(1f, Math.abs(mCurrentDragPercent));
                float extraOS = Math.abs(scrollTop) - mTotalDragDistance;
                float slingshotDist = mTotalDragDistance;
                float tensionSlingshotPercent = Math.max(0,
                        Math.min(extraOS, slingshotDist * 2) / slingshotDist);
                float tensionPercent = (float) ((tensionSlingshotPercent / 4) - Math.pow(
                        (tensionSlingshotPercent / 4), 2)) * 2f;
                float extraMove = (slingshotDist) * tensionPercent / 2;
                int targetY = (int) ((slingshotDist * boundedDragPercent) + extraMove);

                setTargetOffsetTop(targetY - mCurrentOffsetTop, true);
                break;
            case MotionEvent.ACTION_UP:
                mIsBeingDragged = false;
                break;
        }

        return true;
    }

    private void setTargetOffsetTop(int offset, boolean requireUpdate) {
        mTarget.offsetTopAndBottom(offset);
        mCurrentOffsetTop = mTarget.getTop();
        if (requireUpdate && Build.VERSION.SDK_INT < 11){
            invalidate();
        }
    }
}
