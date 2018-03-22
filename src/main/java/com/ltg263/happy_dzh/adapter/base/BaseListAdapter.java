package com.ltg263.happy_dzh.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2017/2/26 14:19
 * 邮箱；ltg263@126.com
 * 描述：基类,主要是简化adapter书写,提供 填充器mInflater 数据mData 上下文mContext
 */

public abstract class BaseListAdapter<Data> extends BaseAdapter {
	
/*	protected ImageOptions options = new ImageOptions.Builder().setConfig(Config.RGB_565)
			.setImageScaleType(ImageView.ScaleType.FIT_XY).build();*/
	/**
	 * 适配器数据
	 */
	protected  List<Data> mData;
	/**
	 * 上下文,供子类直接使用
	 */
	protected Context mContext;
	/**
	 * 填充器,供子类直接使用
	 */
	protected LayoutInflater mInflater;

	public BaseListAdapter(Context context, List<Data> data) {
		super();
		mContext = context;
		mData = data;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData == null ? null : mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setData(List<Data> data) {
		mData.clear();
		mData.addAll(data);
		notifyDataSetChanged();
	}
	public List<Data> getData() {
		return mData;
	}


	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

	/**
	 * @author yujian
	 * @Description Holder基类
	 * @date 2016/5/12 11:09
	 */
	public abstract class BaseViewHolder {

		public abstract void refresh(int position);
	}
}
