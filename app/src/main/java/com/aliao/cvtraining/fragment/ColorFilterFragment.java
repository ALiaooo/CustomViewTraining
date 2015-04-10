package com.aliao.cvtraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.view.CustomColorFilterView;

/**
 * Created by 丽双 on 2015/4/10.
 */
public class ColorFilterFragment extends Fragment {

    private CustomColorFilterView mCustomView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_filter, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        mCustomView = (CustomColorFilterView) view.findViewById(R.id.custom_color_filter);


    }
}
