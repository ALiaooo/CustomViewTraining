package com.aliao.cvtraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.utils.Constants;
import com.aliao.cvtraining.view.paint.BrickView;
import com.aliao.cvtraining.view.paint.DreamEffectView;
import com.aliao.cvtraining.view.paint.ReflectView;
import com.aliao.cvtraining.view.paint.ShaderView;

/**
 * Created by 丽双 on 2015/4/16.
 */
public class ShaderViewFragment extends Fragment {

    private ShaderView mShaderView;
    private BrickView mBrickView;
    private ReflectView mReflectView;
    private DreamEffectView mDreamEffectView;
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            type = getArguments().getString(Constants.TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shader_view, container, false);
        mShaderView = (ShaderView) view.findViewById(R.id.shader_view);
        mBrickView = (BrickView) view.findViewById(R.id.brick_view);
        mReflectView = (ReflectView) view.findViewById(R.id.reflect_view);
        mDreamEffectView = (DreamEffectView) view.findViewById(R.id.dream_effect_view);
        if (type.equals("shader")){
            mShaderView.setVisibility(View.VISIBLE);
            mBrickView.setVisibility(View.GONE);
            mReflectView.setVisibility(View.GONE);
            mDreamEffectView.setVisibility(View.GONE);
        }else if (type.equals("brick")){
            mBrickView.setVisibility(View.VISIBLE);
            mShaderView.setVisibility(View.GONE);
            mReflectView.setVisibility(View.GONE);
            mDreamEffectView.setVisibility(View.GONE);
        }else if (type.equals("reflect")){
            mReflectView.setVisibility(View.VISIBLE);
            mShaderView.setVisibility(View.GONE);
            mBrickView.setVisibility(View.GONE);
            mDreamEffectView.setVisibility(View.GONE);
        }else if (type.equals("dream")){
            mDreamEffectView.setVisibility(View.VISIBLE);
            mShaderView.setVisibility(View.GONE);
            mBrickView.setVisibility(View.GONE);
            mReflectView.setVisibility(View.GONE);
        }
        return view;
    }
}
