package com.ltg263.happy_dzh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * 作者： litongge
 * 时间：  2017/2/27 21:32
 * 邮箱；ltg263@126.com
 * 描述：解决冲突
 */
public class GridViewUtils extends GridView {
	public GridViewUtils(Context context) {
		super(context);
	}

	public GridViewUtils(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GridViewUtils(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}