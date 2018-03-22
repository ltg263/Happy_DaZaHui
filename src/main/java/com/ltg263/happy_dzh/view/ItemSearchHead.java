package com.ltg263.happy_dzh.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.NewsActivity;
import com.ltg263.happy_dzh.activity.SearchListActivity;
import com.ltg263.happy_dzh.fragment.MainFirstFragment;
import com.ltg263.happy_dzh.utils.IntentUtil;
import com.ltg263.happy_dzh.utils.StringUtil;

/**
 * 作者： litongge
 * 时间：  2017/3/5 11:47
 * 邮箱；ltg263@126.com
 * 描述：
 */

public class ItemSearchHead extends LinearLayout implements View.OnClickListener{
    private LinearLayout headSearch;
    private TextView btnSendCode;
    private ImageView ivDeleteText;
    private EditText etSearch;
    private Context mContext;
    private int type = -2;

    public ItemSearchHead(Context context) {
        super(context);
    }

    public ItemSearchHead(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context,attrs);
    }

    private void initView(final Context context, AttributeSet attrs) {
        headSearch = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.head_search_item, this);
        btnSendCode = (TextView) headSearch.findViewById(R.id.btn_send_code);
        ivDeleteText = (ImageView) headSearch.findViewById(R.id.iv_delete_text);
        etSearch = (EditText) headSearch.findViewById(R.id.et_search);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Search_Text);
        String etContent = ta.getString(R.styleable.Search_Text_setSearch);
        etSearch.setHint(etContent);
        btnSendCode.setOnClickListener(this);
        ivDeleteText.setOnClickListener(this);
        etSearchChangedListener();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_code:
                if(type==-2){
                    ((SearchListActivity)mContext).anewSearch(etSearch.getText().toString().trim(),true);
                }else{
                    String key[] = {"word","type","isSearch"};
                    String searchContent = etSearch.getText().toString().trim();
                    if(StringUtil.isBlank(searchContent)){
                        Dialog_Exit.showDialogNoListen(mContext,mContext.getString(R.string.search_content));
                        return;
                    }
                    String value="";
                    if(type == MainFirstFragment.FRAGMENT_PIN_YIN){
                        value = MainFirstFragment.FRAGMENT_PIN_YIN+"";
                    }else if(type == MainFirstFragment.FRAGMENT_BU_SHOU){
                        value = MainFirstFragment.FRAGMENT_BU_SHOU+"";
                    }else{
                        IntentUtil.startActivity(NewsActivity.class,key,new String[]{searchContent,"123",""});
                        return;
                    }
                    String values[] = {searchContent, value,"true"};
                    IntentUtil.startActivity(SearchListActivity.class,key,values);
                }
                break;
            case R.id.iv_delete_text:
                etSearch.setText("");
                break;
        }
    }
    public void setSearchHint(String hint){
        etSearch.setHint(hint);
    }
    public void setSearchText(String text){
        etSearch.setText(text);
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSearchText(){
        return etSearch.getText().toString().trim();
    }
    /**
     * 监听输入框输入文字长度
     */
    public void etSearchChangedListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(type == MainFirstFragment.FRAGMENT_BU_SHOU){
                    etSearch.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
                }else{
                    etSearch.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    btnSendCode.setSelected(true);
                    ivDeleteText.setVisibility(View.VISIBLE);
                } else {
                    btnSendCode.setSelected(false);
                    ivDeleteText.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
