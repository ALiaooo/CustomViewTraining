package com.aliao.cvtraining.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.view.widget.CustomTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/8/20.
 */
public class ViewDrawingProcedureActivity extends AppCompatActivity {

    private final int LIST_DATA_COUNT = 20;

    private ListView mListView;

    private List<String> mListData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drawing_procedure);//activity_view_drawing_procedure
//        initDataSet();
//        initInstances();
    }

    private void initDataSet() {
        mListData = new ArrayList<>();
        for (int i = 0; i< LIST_DATA_COUNT; i++){
            mListData.add("item #"+i);
        }
    }


    private void initInstances() {
        mListView = (ListView) findViewById(R.id.listview);
        MyAdapter mAdapter  = new MyAdapter(mListData);
        mListView.setAdapter(mAdapter);
    }

    class MyAdapter extends BaseAdapter{

        private List<String> mListData;

        public MyAdapter(List<String> listData) {
            mListData = listData;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_common, parent,false);
                holder.textview = (CustomTextView) convertView.findViewById(R.id.textview);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textview.setText(mListData.get(position));
            return convertView;
        }

        private class ViewHolder {
            CustomTextView textview;
        }
    }
}
