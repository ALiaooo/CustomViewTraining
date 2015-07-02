package com.aliao.cvtraining.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aliao.cvtraining.R;
import com.aliao.cvtraining.entity.Option;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.view.widget.CheckedOptionView;

import java.util.List;

/**
 * Created by 丽双 on 2015/7/2.
 */
public class OptionAdapter extends BaseAdapter {

    private List<Option> mOptions;

    public OptionAdapter(List<Option> optionList) {
        mOptions = optionList;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        Option option = mOptions.get(position);

        final ViewHolder holder;

        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);
//            holder.optionView = (CheckedOptionView) convertView.findViewById(R.id.checkedOptionView);
            holder.optionView = (TextView) convertView.findViewById(R.id.checkedOptionView);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        //判断选项是否为开放选项，如果是，则该选项后显示文本框供用户自动输入
        if (null != option) {
            L.d("getView refresh");
//            holder.optionView.setSelected(option.isSelected());
            holder.optionView.setText(option.getTitle());
//            holder.optionView.setOptionType(option.isOpen()?"open":"normal");
        }

        return convertView;
    }


    class ViewHolder{
//        private CheckedOptionView optionView;
        private TextView optionView;
    }
}
