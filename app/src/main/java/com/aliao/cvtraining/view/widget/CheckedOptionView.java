package com.aliao.cvtraining.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.L;


/**
 * Created by 丽双 on 2015/7/1.
 */
public class CheckedOptionView extends LinearLayout implements Checkable {

    private final int OPEN_OPTION = 1;
    private final int NORMAL_OPTION = 0;
    private boolean mChecked;
    private int optionType;
    private CharSequence mTitleText;
    private ColorStateList mTitleColor;
    private float mTitleSize = 14;
    private TextView mTvTitle;
    private EditText mEtOpen;

    public CheckedOptionView(Context context) {
        this(context, null);
    }

    public CheckedOptionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CheckedOptionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckedOptionView);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CheckedOptionView_titletext:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.CheckedOptionView_titlecolor:
                    mTitleColor = typedArray.getColorStateList(attr);
                    break;
                case R.styleable.CheckedOptionView_titlesize:
                    mTitleSize = typedArray.getDimensionPixelSize(attr, 14);
                    break;
                case R.styleable.CheckedOptionView_optionType:
                    optionType = typedArray.getInt(attr, 0);
                    break;
            }
        }

        mTvTitle = new TextView(context);

        setText(mTitleText);
        setTextColor(mTitleColor);
        setTextSize(mTitleSize);
        addView(mTvTitle);

        mEtOpen = new EditText(context);
        initEditView(mEtOpen);
        addView(mEtOpen);

        mEtOpen.setVisibility(optionType == OPEN_OPTION ? View.VISIBLE : View.GONE);

        setOrientation(LinearLayout.VERTICAL);

        typedArray.recycle();
    }

    public void setText(CharSequence titleText) {
        mTitleText = titleText;
        mTvTitle.setText(titleText);
    }

    public void setTextColor(ColorStateList colors){
        mTvTitle.setTextColor(colors != null ? colors : ColorStateList.valueOf(0xFF000000));
    }

    public void setTextSize(float size){
        mTvTitle.setTextSize(size);
    }

    public CharSequence getText() {
        return (CharSequence)(mEtOpen != null?TextUtils.isEmpty(mEtOpen.getText())?mEtOpen.getText().toString().trim():null:null);
    }

    public void setOptionType(String optionType) {
        if(optionType.equals("open")){
            this.optionType = OPEN_OPTION;
            mEtOpen.setVisibility(VISIBLE);
        }else if (optionType.equals("normal")){
            this.optionType = NORMAL_OPTION;
            mEtOpen.setVisibility(GONE);
        }
        requestLayout();
    }

    private void initEditView(EditText et) {
        et.setBackgroundResource(R.drawable.bg_option_edittext);
        et.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setChecked(true);
                } else {
                    setChecked(false);
                }
            }
        });
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(layoutParams);
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        L.d("toggle = " + !mChecked);
        setChecked(!mChecked);
    }


    public void setChecked(boolean checked) {
        L.d("setChecked = "+checked);
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
            /*notifyViewAccessibilityStateChangedIfNeeded(
                    AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED);*/
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };


    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(CheckedOptionView.class.getName());
        event.setChecked(mChecked);
    }


    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(CheckedOptionView.class.getName());
        info.setCheckable(true);
        info.setChecked(mChecked);
    }

}
