package com.aliao.cvtraining.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aliao.cvtraining.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/8/19.
 */
public class ScrollViewPagerActivity extends AppCompatActivity{

    private final int DATA_COUNT = 4;
    private final int LIST_DATA_COUNT = 17;
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;
    private String[] mDataSet;
    private ListView mListview;
    private List<String> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_viewpager);
        initDataSet();
        initInstances();
    }

    private void initDataSet() {
        mDataSet = new String[DATA_COUNT];
        for (int i = 0; i<DATA_COUNT; i++){
            mDataSet[i] = "item #"+i;
        }
        mListData = new ArrayList<>();
        for (int i = 0; i< LIST_DATA_COUNT; i++){
            mListData.add("item #"+i);
        }
    }

    private void initInstances() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mDataSet);
        mViewPager.setAdapter(mPagerAdapter);

        mListview = (ListView) findViewById(R.id.listview);
        ArrayAdapter mAdapter  = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mListData);
        mListview.setAdapter(mAdapter);
    }

    class MyPagerAdapter extends FragmentPagerAdapter{
        private String[] mDataSet;

        public MyPagerAdapter(FragmentManager fm, String[] dataSet) {
            super(fm);
            mDataSet = dataSet;
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragment.newInstance(mDataSet[position]);
        }

        @Override
        public int getCount() {
            return mDataSet.length;
        }
    }
    public static class ContentFragment extends Fragment{

        private String mData;

        public static ContentFragment newInstance(String data) {

            Bundle args = new Bundle();
            args.putString("data", data);
            ContentFragment fragment = new ContentFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null){
                mData = getArguments().getString("data");
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_content, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            TextView textview = (TextView) view.findViewById(R.id.tv_content);
            textview.setText(mData);
        }
    }
}
