package com.aliao.cvtraining.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.adapter.OptionAdapter;
import com.aliao.cvtraining.entity.Option;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.view.widget.CheckedEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丽双 on 2015/7/1.
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

        mListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        for (int i = 0; i<5; i++){
            Option option = new Option();
            if (i == 3){
                option.setOpen(true);
            }else option.setOpen(false);

            option.setTitle("item - " + i);
            optionList.add(option);
        }
        mAdapter = new OptionAdapter(optionList);
        mListView.setAdapter(mAdapter);



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
