package com.aliao.cvtraining.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aliao.cvtraining.R;
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

    public OptionAdapter(List<Option> optionList) {
        mOptions = optionList;
    }

    public void addChoicedOption(int position){
        L.d("addChoicedOption");
        for (Option option : mOptions){
            option.setSelected(false);
        }
        mOptions.get(position).setSelected(true);
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

        Option option = mOptions.get(position);

        final ViewHolder holder;

        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);
            holder.optionView = (CheckedOptionView) convertView.findViewById(R.id.checkedOptionView);
//            holder.optionView = (TextView) convertView.findViewById(R.id.checkedOptionView);
//            holder.optionOpen = (EditText) convertView.findViewById(R.id.openOption);
//            holder.optionContainer = (LinearLayout) convertView.findViewById(R.id.optionContainer);
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

      /*  holder.optionView.setOnEtClickListener(new CheckedOptionView.OnEtClickListener() {
            @Override
            public void onClick(View v) {
                addChoicedOption(position);
                L.d("execute onclick");
            }
        });*/
        holder.optionView.setOnEtTouchListener(new CheckedOptionView.OnEtTouchListener() {
            @Override
            public void onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    L.d("execute onTouch");
                    addChoicedOption(position);
                    mTouchedPosition = position;
                }
            }
        });

        if (null != option) {
            L.d(position +" - getView refresh");
            holder.optionView.setChecked(option.isSelected());
            holder.optionView.setText(option.getTitle());
            holder.optionView.setOptionType(option.isOpen()?"open":"normal");
        }

        holder.optionView.clearEtFocus();
        if (mTouchedPosition!=-1 && mTouchedPosition == position) {
            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
            L.d("requestFocus");
            holder.optionView.requestEtFocus();
        }

        return convertView;
    }

    class ViewHolder{
        private CheckedOptionView optionView;
//        private TextView optionView;
//        private EditText optionOpen;
//        private LinearLayout optionContainer;
    }
}
