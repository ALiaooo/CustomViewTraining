package com.aliao.cvtraining;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.aliao.cvtraining.fragment.BeautyDisInFragment;
import com.aliao.cvtraining.fragment.CircularRingFragment;
import com.aliao.cvtraining.fragment.ColorFilterFragment;
import com.aliao.cvtraining.fragment.ECGViewFragment;
import com.aliao.cvtraining.fragment.EraserViewFragment;
import com.aliao.cvtraining.fragment.FontViewFragment;
import com.aliao.cvtraining.fragment.MaskFilterFragment;
import com.aliao.cvtraining.fragment.PathEffectFragment;
import com.aliao.cvtraining.fragment.PorterDuffViewFragment;
import com.aliao.cvtraining.fragment.ShaderViewFragment;
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
            case Constants.FONT_VIEW_INTEX:
                L.d("字体");
                if (null == fragment)
                    fragment = new FontViewFragment();
                break;
            case Constants.MASK_FILTER_VIEW_INTEX:
                L.d("模糊遮罩滤镜和浮雕遮罩滤镜");
                if (null == fragment)
                    fragment = new MaskFilterFragment();
                break;
            case Constants.PATH_EFFECT_VIEW_INTEX:
                L.d("路径效果");
                if (null == fragment)
                    fragment = new PathEffectFragment();
                break;
            case Constants.ECG_VIEW_INTEX:
                L.d("心电图");
                if (null == fragment)
                    fragment = new ECGViewFragment();
                break;
            case Constants.SHADER_VIEW_INTEX:
                L.d("Shader View");
                if (null == fragment)
                    fragment = new ShaderViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TYPE, "shader");
                fragment.setArguments(bundle);
                break;
            case Constants.BRICK_VIEW_INTEX:
                L.d("Shader View");
                if (null == fragment)
                    fragment = new ShaderViewFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString(Constants.TYPE, "brick");
                fragment.setArguments(bundle1);
                break;
            case Constants.REFLECT_VIEW_INTEX:
                L.d("图片倒影效果");
                if (null == fragment)
                    fragment = new ShaderViewFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString(Constants.TYPE, "reflect");
                fragment.setArguments(bundle2);
                break;
            case Constants.DREAM_EFFECT_VIEW_INTEX:
                L.d("美女图梦幻效果");
                if (null == fragment)
                    fragment = new ShaderViewFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString(Constants.TYPE, "dream");
                fragment.setArguments(bundle3);
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

    }

}
