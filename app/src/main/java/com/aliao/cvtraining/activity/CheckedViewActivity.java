package com.aliao.cvtraining.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.view.widget.CheckedEditText;
import com.aliao.cvtraining.view.widget.CheckedLinearLayout;

/**
 * Created by 丽双 on 2015/7/1.
 */
public class CheckedViewActivity extends Activity implements View.OnClickListener{

    private CheckedTextView checkedTextView;
    private CheckedEditText checkedEditText;
    private CheckedLinearLayout checkedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkedview);
        initViews();
    }

    private void initViews() {
        checkedTextView = (CheckedTextView) findViewById(R.id.ctx);
        checkedEditText = (CheckedEditText) findViewById(R.id.cetx);
        checkedLayout = (CheckedLinearLayout) findViewById(R.id.clayout);

        checkedTextView.setOnClickListener(this);
        checkedEditText.setOnClickListener(this);
        checkedLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ctx:
                checkedTextView.toggle();
                L.d("checkedTextView check state = "+checkedTextView.isChecked());
                break;
            case R.id.cetx:
                checkedEditText.toggle();
                L.d("checkedEditText check state = " + checkedEditText.isChecked());
                if (checkedEditText.isChecked()){
                    checkedEditText.setFocusable(true);
                    checkedEditText.setFocusableInTouchMode(true);
                }
                break;
            case R.id.clayout:
                checkedLayout.toggle();
                L.d("checkedLayout check state = " + checkedLayout.isChecked());
                break;
        }
    }
}
