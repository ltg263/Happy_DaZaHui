package com.ltg263.happy_dzh.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.adapter.base.BaseListAdapter;
import com.ltg263.happy_dzh.baseManager.BaseApp;
import com.ltg263.happy_dzh.baseManager.BaseManager;
import com.ltg263.happy_dzh.bean.NewsDetailsBean;
import com.ltg263.happy_dzh.utils.ImageLoader;
import com.ltg263.happy_dzh.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者： litongge
 * 时间：  2017/2/26 14:22
 * 邮箱；ltg263@126.com
 * 描述：配置左侧菜单的Adapter
 */

public class NewsDetailsAdapter extends BaseListAdapter<NewsDetailsBean.ResultBean> {



    public NewsDetailsAdapter(Context context, List<NewsDetailsBean.ResultBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hold;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news_details, null);
            hold = new ViewHolder(convertView);
            convertView.setTag(hold);
        } else {
            hold = (ViewHolder) convertView.getTag();
        }
        hold.refresh(position);
        return convertView;
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_img_icon)
        ImageView ivImgIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_src)
        TextView tvSrc;
        @BindView(R.id.tv_date)
        TextView tvDate;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void refresh(int position) {
            NewsDetailsBean.ResultBean contentModel = mData.get(position);
            ImageLoader.init(BaseManager.app);
            if(StringUtil.isNotBlank(contentModel.getImg())){
                ImageLoader.get().load(ivImgIcon, Uri.parse(contentModel.getImg()));
            }else{
                ImageLoader.get().load(ivImgIcon,R.drawable.new_sample);
            }
            tvTitle.setText(contentModel.getTitle());
            tvContent.setText(contentModel.getContent());
            tvSrc.setText("新闻来源："+contentModel.getSrc());
            tvDate.setText("时间："+contentModel.getPdate());
        }
    }
}
