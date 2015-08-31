package com.aliao.cvtraining.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.aliao.cvtraining.R;

/**
 * Created by 丽双 on 2015/8/27.
 */
public class CustomShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_show);
        LinearLayout l = new LinearLayout(this);
        l.setGravity(Gravity.TOP);
    }
}
