package com.aliao.cvtraining.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.adapter.OptionAdapter;
import com.aliao.cvtraining.entity.Option;

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
public class CheckedViewActivity extends Activity implements View.OnClickListener {

    private String[] mMusicType = {"R&B", "Folk", "重金属", "其他"};
    private TextView mTvQuestionTitle;
    private OptionAdapter mAdapter;
    private ListView mListView;
    private List<Option> optionList = new ArrayList<>();
    private  List<String> musicStyleList = new ArrayList<>();


    public interface QType {
        int SINGLE = 1;         //单选题
        int MULTIPLE = 2;       //多选题
        int MARK = 3;           //打分题（单）
        int TRUEORFALSE = 4;    //是非题（单）
        int OPEN = 5;           //开放题
        int TEXT = 6;           //文字描述题
        int SORT = 7;           //排序题
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkedview);
        initViews();
    }

    private void initViews() {

        mTvQuestionTitle = (TextView) findViewById(R.id.tvQuestionTitle);
        mTvQuestionTitle.setText("你喜欢听什么类型的音乐？");
        findViewById(R.id.btn_single).setOnClickListener(this);
        findViewById(R.id.btn_multiple).setOnClickListener(this);
        findViewById(R.id.btn_open).setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.listview);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mAdapter.addChoicedOption(position);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_single:
                initData(QType.SINGLE);
                break;
            case R.id.btn_multiple:
                initData(QType.MULTIPLE);
                break;
            case R.id.btn_open:
                initData(QType.OPEN);
                break;

        }
    }

    private void initData(int type){

        String typeStr = null;
        switch (type){
            case QType.SINGLE:
                typeStr = "(单选题)";
                buildData();
                break;
            case QType.MULTIPLE:
                typeStr = "(多选题)";
                buildData();
                break;
            case QType.OPEN:
                typeStr = "(开放题)";
                buildOpenData();
                break;
        }
        mTvQuestionTitle.setText("你喜欢听什么类型的音乐？"+typeStr);

        optionList.clear();
        for (int i = 0; i<musicStyleList.size(); i++){
            Option option = new Option();
            option.setTitle(musicStyleList.get(i));
            option.setSelected(false);
            if (i == mMusicType.length-1){
                option.setOpen(true);
            }else {
                option.setOpen(false);
            }
            optionList.add(option);
        }

        mAdapter = new OptionAdapter(optionList, type);
        mListView.setAdapter(mAdapter);
    }

    private void buildData(){
        musicStyleList.clear();
        musicStyleList.add("R&B");
        musicStyleList.add("Folk");
        musicStyleList.add("重金属");
        musicStyleList.add("其他");
    }
    private void buildOpenData(){
        musicStyleList.clear();
        musicStyleList.add("");
    }

}
