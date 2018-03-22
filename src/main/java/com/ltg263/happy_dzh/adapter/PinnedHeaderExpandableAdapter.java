package com.ltg263.happy_dzh.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.SearchListActivity;
import com.ltg263.happy_dzh.bean.PinyinOrBiHuaBean;
import com.ltg263.happy_dzh.fragment.MainFirstFragment;
import com.ltg263.happy_dzh.utils.IntentUtil;
import com.ltg263.happy_dzh.view.GridViewUtils;
import com.ltg263.happy_dzh.view.PinnedHeaderExpandableListView;

import java.util.List;
import java.util.Map;

/**
 * 作者： litongge
 * 时间： 2017/3/3 10:32
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PinnedHeaderExpandableAdapter extends  BaseExpandableListAdapter implements PinnedHeaderExpandableListView.HeaderAdapter {
	private Map<String, List<PinyinOrBiHuaBean.ResultBean>> groupData;
	private List<String> keyData;
	private Context context;
	private PinnedHeaderExpandableListView listView;
	private LayoutInflater inflater;
	private int type;
	public PinnedHeaderExpandableAdapter(List<String> keyData, Map<String, List<PinyinOrBiHuaBean.ResultBean>> groupData
			, Context context, PinnedHeaderExpandableListView listView,int type){
		this.groupData = groupData;
		this.keyData = keyData;
		this.context = context;
		this.listView = listView;
		this.type=type;
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groupData.get(keyData.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		GridViewUtils gridview;
        if (convertView == null) {
			convertView = inflater.inflate(R.layout.elv_item_child, null);
			gridview = (GridViewUtils)convertView.findViewById(R.id.gridview);
			gridview.setVisibility(View.VISIBLE);
			convertView.setTag(gridview);
		} else {
			gridview = (GridViewUtils) convertView.getTag();
        }
		ChildViewAdapter adapter = new ChildViewAdapter(context,groupData.get(keyData.get(groupPosition)),type);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int a = 0;
				String key[] = {"word","type"};
				String values[];
				if(type == MainFirstFragment.FRAGMENT_PIN_YIN){
					values=new String[]{groupData.get(keyData.get(groupPosition)).get(position).getPinyin(),type+""};
				}else{
					values=new String[]{groupData.get(keyData.get(groupPosition)).get(position).getBushou(),type+""};

				}
				IntentUtil.startActivity(SearchListActivity.class,key,values);
			}
		});
        return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
//		return groupData.get(keyData.get(groupPosition)).size();
		//使用的是gridview
		return 1;
	}

	@Override
	public String getGroup(int groupPosition) {
		return keyData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {
		ViewHolder holder;
        if (convertView == null) {
			holder=new ViewHolder();
			convertView = inflater.inflate(R.layout.elv_item_group, null);
			holder.iv = (ImageView)convertView.findViewById(R.id.groupIcon);
			holder.text = (TextView)convertView.findViewById(R.id.groupto);
			holder.groupto_count = (TextView)convertView.findViewById(R.id.groupto_count);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refresh(isExpanded,groupPosition);

        return convertView;
	}
	class ViewHolder{
		ImageView iv;
		TextView text;
		TextView groupto_count;

		public void refresh(boolean isExpanded, int groupPosition) {
			if (isExpanded) {
				iv.setImageResource(R.drawable.btn_browser2);
			}else{
				iv.setImageResource(R.drawable.btn_browser);
			}
			text.setText(keyData.get(groupPosition));
			groupto_count.setText(groupData.get(keyData.get(groupPosition)).size()+"个");
		}
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	


	@Override
	public int getHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1&& !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	@Override
	public void configureHeader(View header, int groupPosition,int childPosition, int alpha) {
		((TextView) header.findViewById(R.id.groupto)).setText(keyData.get(groupPosition));
		((TextView) header.findViewById(R.id.groupto_count)).setText(groupData.get(keyData.get(groupPosition)).size()+"个");

	}
	
	private SparseIntArray groupStatusMap;

	@Override
	public void setGroupClickStatus(int groupPosition, int status) {
		if(groupStatusMap==null && keyData!=null){
			groupStatusMap = new SparseIntArray(keyData.size());
		}
		if(groupStatusMap!=null){
			groupStatusMap.put(groupPosition, status);
		}
	}

	@Override
	public int getGroupClickStatus(int groupPosition) {
		if(groupStatusMap==null && keyData!=null){
			groupStatusMap = new SparseIntArray(keyData.size());
		}
		if(groupStatusMap!=null){
			if (groupStatusMap.keyAt(groupPosition)>=0) {
				return groupStatusMap.get(groupPosition);
			}
		}
		return 0;
	}
}
