package com.aliao.cvtraining.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.adapter.OptionAdapter;
import com.aliao.cvtraining.entity.Option;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.view.widget.CheckedEditText;
import com.aliao.cvtraining.view.widget.CheckedOptionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/7/1.
 *关于ListView中EditText在软键盘弹出后的焦点问题
 * http://www.cnblogs.com/ycxyyzw/p/4150436.html
 *
 * EditText setOnClickListener事件 只有获取焦点才能响应 采用s
 * 发现EditText setOnClickListener事件响应中，只有获取焦点的时候才会响应，如当焦点在别的控件上时，只能先点击获取焦点，第二次点击才会响应
 * http://bbs.9ria.com/thread-240825-1-1.html
 */
public class CheckedViewActivity extends Activity implements View.OnClickListener{

    private CheckedTextView checkedTextView;
    private CheckedEditText checkedEditText;
//    private CheckedLinearLayout checkedLayout;
    private OptionAdapter mAdapter;
    private ListView mListView;
    private List<Option> optionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkedview);
        initViews();
    }

    private void initViews() {
        checkedTextView = (CheckedTextView) findViewById(R.id.ctx);
        checkedEditText = (CheckedEditText) findViewById(R.id.cetx);

        checkedTextView.setOnClickListener(this);
        checkedEditText.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.listview);

        for (int i = 0; i<1; i++){
            Option option = new Option();
            if (i == 0){
                option.setOpen(true);
            }else option.setOpen(false);

            option.setTitle("item - " + i);
            optionList.add(option);
        }
        mAdapter = new OptionAdapter(optionList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CheckedOptionView optionView = (CheckedOptionView) view.findViewById(R.id.checkedOptionView);
//                optionView.setFocusable(true);
//                optionView.toggle();
                mAdapter.addChoicedOption(position);
            }
        });


        Button button = (Button) findViewById(R.id.btn_change);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAdapter.notifyDataSetChanged();
            }
        });

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

        }
    }

}
