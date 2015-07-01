package com.aliao.cvtraining.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/7/1.
 *
 * 如果text为null的处理
 *
 */
public class CheckedLinearLayout extends LinearLayout implements Checkable{

    private final int OPEN_OPTION = 1;
    private final int NORMAL_OPTION = 0;
    private boolean mChecked;
    private boolean etHasFocus;
    private int optionType;
    private String titleText;
    private ColorStateList titleColor;

    public CheckedLinearLayout(Context context) {
        this(context, null);
    }

    public CheckedLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CheckedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckedLinearLayout);
        TextView tv = new TextView(context);

        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CheckedLinearLayout_titletext:
                    titleText = typedArray.getString(attr);
                    break;
                case R.styleable.CheckedLinearLayout_titlecolor:
                    titleColor = typedArray.getColorStateList(attr);
                    break;
                case R.styleable.CheckedLinearLayout_optionType:
                    optionType = typedArray.getInt(attr, 0);
                    break;
            }
        }


        if (!TextUtils.isEmpty(titleText)){
            tv.setText(titleText);
            tv.setTextColor(titleColor != null ? titleColor : ColorStateList.valueOf(0xFF000000));
            addView(tv);
        }

        if (optionType == OPEN_OPTION){
            EditText et = new EditText(context);
            setEditView(et);
            addView(et);
        }

        setOrientation(LinearLayout.VERTICAL);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etHasFocus){

                }
            }
        });


        typedArray.recycle();

    }

    private void setTextView(TextView tv) {
        //注意:
        //1  此处的new RelativeLayout.LayoutParams(int w, int h)参数w,h指的是
        //  该控件在布局文件中所设置的宽和高
        //2  同测试1中的描述
/*        RelativeLayout.LayoutParams layoutParams=
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);*/

       /* LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        layoutParams1.gravity = Gravity.CENTER;
        tv.setLayoutParams(layoutParams1);*/
        //位置的设置，可以换成去extends RelayoutLayout
        tv.setBackgroundResource(R.color.bright_foreground_disabled_material_dark);
        addView(tv);
    }

    public void setOptionType(int optionType) {
        this.optionType = optionType;
        requestLayout();
    }

    private void setEditView(EditText et) {
        et.setBackgroundResource(R.drawable.bg_option_edittext);
        et.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                etHasFocus = hasFocus;
                if (hasFocus) {
                    setChecked(true);
                } else {
                    setChecked(false);
                }
            }
        });

    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }


    public void setChecked(boolean checked) {
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

    /**
     * The stable ID of this view for accessibility purposes.
     */
    int mAccessibilityViewId = NO_ID;

//    private int mAccessibilityCursorPosition = ACCESSIBILITY_CURSOR_POSITION_UNDEFINED;
//
//    SendViewStateChangedAccessibilityEvent mSendViewStateChangedAccessibilityEvent;


    /**
     * Notifies that the accessibility state of this view changed. The change
     * is local to this view and does not represent structural changes such
     * as children and parent. For example, the view became focusable. The
     * notification is at at most once every
     * {@link ViewConfiguration#getSendRecurringAccessibilityEventsInterval()}
     * to avoid unnecessary load to the system. Also once a view has a pending
     * notification this method is a NOP until the notification has been sent.
     *
     * @hide
     */
    /*public void notifyViewAccessibilityStateChangedIfNeeded(int changeType) {
        if (!AccessibilityManager.getInstance(mContext).isEnabled()) {
            return;
        }
        if (mSendViewStateChangedAccessibilityEvent == null) {
            mSendViewStateChangedAccessibilityEvent =
                    new SendViewStateChangedAccessibilityEvent();
        }
        mSendViewStateChangedAccessibilityEvent.runOrPost(changeType);
    }
*/

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(CheckedLinearLayout.class.getName());
        event.setChecked(mChecked);
    }


    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(CheckedLinearLayout.class.getName());
        info.setCheckable(true);
        info.setChecked(mChecked);
    }
}
