package com.aliao.cvtraining.adapter;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.activity.CheckedViewActivity;
import com.aliao.cvtraining.entity.Option;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.view.widget.CheckedOptionView;

import java.util.List;

/**
 * Created by ALiao on 2015/7/2.
 */
public class OptionAdapter extends BaseAdapter {

    private List<Option> mOptions;
    private int mTouchedPosition;
    private int mQType;

    public OptionAdapter(List<Option> optionList, int qType) {
        mQType = qType;
        mOptions = optionList;
    }

    public void addChoicedOption(int position){
        if (mQType == CheckedViewActivity.QType.SINGLE){
            for (Option option : mOptions){
                option.setSelected(false);
            }
        }
        Option option = mOptions.get(position);
        option.setSelected(mQType == CheckedViewActivity.QType.MULTIPLE ? !option.isSelected() : true);//开放题一直是选中状态
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mOptions.size();
    }

    @Override
    public Object getItem(int position) {
        return mOptions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Option option = mOptions.get(position);

        final ViewHolder holder;

        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);
            holder.optionView = (CheckedOptionView) convertView.findViewById(R.id.checkedOptionView);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.optionView.setOnEtTouchListener(new CheckedOptionView.OnEtTouchListener() {
            @Override
            public void onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    //如果当前多选开放选项已经是选中状态，再次点击禁止弹出对话框
                    mTouchedPosition = isMultipleQuestionOpenOption(option) ? -1 : position;
                    addChoicedOption(position);
                }
            }
        });


        holder.optionView.addEtTextWatcher(new CheckedOptionView.OnEtTextWatcher() {
            @Override
            public void afterTextChange(Editable s) {
                if (s.toString() != null)
                    option.setOpenAnswer(s.toString());
            }
        });

        if (null != option) {
            holder.optionView.setChecked(option.isSelected());
            holder.optionView.setText(option.getTitle());
            holder.optionView.setOpenText(option.getOpenAnswer());
            holder.optionView.setOptionType(isOpenType(option)?"open":"normal");
        }

        holder.optionView.clearEtFocus();
        if (mTouchedPosition != -1 && mTouchedPosition == position) {
            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
            holder.optionView.requestEtFocus();
        }else if (mTouchedPosition == -1){
            holder.optionView.cancelEtFocus();
        }

        return convertView;
    }

    private boolean isMultipleQuestionOpenOption(Option option){
        return mQType == CheckedViewActivity.QType.MULTIPLE && option.isSelected() && option.isOpen();
    }

    private boolean isOpenType(Option option){
        return option.isOpen() || mQType == CheckedViewActivity.QType.OPEN;
    }


    class ViewHolder{
        private CheckedOptionView optionView;
    }
}
