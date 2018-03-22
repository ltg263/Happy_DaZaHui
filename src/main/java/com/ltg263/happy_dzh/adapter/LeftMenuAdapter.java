package com.ltg263.happy_dzh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.adapter.base.BaseListAdapter;
import com.ltg263.happy_dzh.bean.ContentModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者： litongge
 * 时间：  2017/2/26 14:22
 * 邮箱；ltg263@126.com
 * 描述：配置左侧菜单的Adapter
 */

public class LeftMenuAdapter extends BaseListAdapter<ContentModel> {

    public LeftMenuAdapter(Context context, List<ContentModel> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hold;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_left_content, null);
            hold = new ViewHolder(convertView);
            convertView.setTag(hold);
        } else {
            hold = (ViewHolder) convertView.getTag();
        }
        hold.refresh(position);
        return convertView;
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.item_imageview)
        ImageView itemImageview;
        @BindView(R.id.item_textview)
        TextView itemTextview;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void refresh(int position) {
            ContentModel contentModel = mData.get(position);
            itemImageview.setImageResource(contentModel.getImageView());
            itemTextview.setText(contentModel.getText());
        }
    }
}
