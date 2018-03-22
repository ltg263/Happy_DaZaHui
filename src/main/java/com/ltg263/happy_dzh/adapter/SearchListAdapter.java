package com.ltg263.happy_dzh.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.adapter.base.BaseListAdapter;
import com.ltg263.happy_dzh.bean.SearchBean;
import com.ltg263.happy_dzh.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间：  2017/3/5 12:28
 * 邮箱；ltg263@126.com
 * 描述：搜索数据的ITEM
 */
public class SearchListAdapter extends BaseListAdapter<SearchBean.ResultBean.ListBean> {

    public SearchListAdapter(Context context, List<SearchBean.ResultBean.ListBean> listBeen) {
        super(context, listBeen);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hold;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search_content, null);
            hold = new ViewHolder(convertView);
            convertView.setTag(hold);
        } else {
            hold = (ViewHolder) convertView.getTag();
        }
        hold.refresh(position);
        return convertView;
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_search_zi)
        TextView tvSearchZi;
        @BindView(R.id.tv_search_bs)
        TextView tvSearchBs;
        @BindView(R.id.tv_search_bh)
        TextView tvSearchBh;
        @BindView(R.id.tv_search_wb)
        TextView tvSearchWb;
        @BindView(R.id.tv_search_py)
        TextView tvSearchPy;
        @BindView(R.id.tv_search_dy)
        TextView tvSearchDy;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Override
        public void refresh(int position) {
            SearchBean.ResultBean.ListBean result = mData.get(position);
            tvSearchZi.setText(result.getBushou());
            tvSearchZi.setText(result.getZi());
            tvSearchPy.setText(Html.fromHtml(String.format(mContext.getString(R.string.xhzd_qy), getTextStr(result.getPy()))));
            tvSearchBh.setText(Html.fromHtml(String.format(mContext.getString(R.string.xhzd_bh), getTextStr(result.getBihua()))));
            tvSearchBs.setText(Html.fromHtml(String.format(mContext.getString(R.string.xhzd_bs), getTextStr(result.getBushou()))));
            tvSearchDy.setText(Html.fromHtml(String.format(mContext.getString(R.string.xhzd_dy), getTextStr(result.getPinyin()))));
            tvSearchWb.setText(Html.fromHtml(String.format(mContext.getString(R.string.xhzd_wb), getTextStr(result.getWubi().toUpperCase()))));
        }

        private String getTextStr(String str){
            if(StringUtil.isNotBlank(str)){
                return str;
            }
            return "------";
        }
    }

}
