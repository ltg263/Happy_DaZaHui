package com.ltg263.happy_dzh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltg263.happy_dzh.adapter.base.BaseListAdapter;
import com.ltg263.happy_dzh.bean.PinyinOrBiHuaBean;
import com.ltg263.happy_dzh.fragment.MainFirstFragment;

import java.util.List;



public class ChildViewAdapter extends BaseListAdapter<PinyinOrBiHuaBean.ResultBean> {
    int type;
    public ChildViewAdapter(Context context, List<PinyinOrBiHuaBean.ResultBean> list,int type) {
        super(context,list);
        this.type=type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView childto;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.elv_item_child, null);
            childto = (TextView)convertView.findViewById(R.id.childto);
            childto.setVisibility(View.VISIBLE);
            convertView.setTag(childto);
        } else {
            childto = (TextView) convertView.getTag();
       }
        if(type== MainFirstFragment.FRAGMENT_PIN_YIN){
            childto.setText(mData.get(position).getPinyin());
        }else{
            childto.setText(mData.get(position).getBushou());
        }
        if(position%2==0){
            childto.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundColor));
        }else{
            childto.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        return convertView;
    }
}
