package com.aliao.cvtraining;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.aliao.cvtraining.fragment.BeautyDisInFragment;
import com.aliao.cvtraining.fragment.CircularRingFragment;
import com.aliao.cvtraining.fragment.ColorFilterFragment;
import com.aliao.cvtraining.fragment.EraserViewFragment;
import com.aliao.cvtraining.fragment.PorterDuffViewFragment;
import com.aliao.cvtraining.utils.Constants;
import com.aliao.cvtraining.utils.L;

/**
 * Created by 丽双 on 2015/4/10.
 *
 */
public class AigeActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = null;

        switch (getIntent().getIntExtra(Constants.FRAGMENT_INDEX, 0)){

            case Constants.CIRCULAR_TING_INTEX:
                L.d("圆环");
                if (null == fragment)
                    fragment = new CircularRingFragment();
                break;

            case Constants.COLOR_FILTER_INTEX:
                L.d("颜色过滤器");
                if (null == fragment)
                    fragment = new ColorFilterFragment();
                break;
            case Constants.POTER_DUFF_INTEX:
                L.d("图形混合模式");
                if (null == fragment)
                    fragment = new PorterDuffViewFragment();
                break;
            case Constants.BEAUTY_POTER_DUFF_INTEX:
                L.d("图形混合模式 美女图演示");
                if (null == fragment)
                    fragment = new BeautyDisInFragment();
                break;
            case Constants.ERASER_VIEW_INTEX:
                L.d("PorterDuffXfermode应用 - 橡皮擦");
                if (null == fragment)
                    fragment = new EraserViewFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

    }

}
