package com.ltg263.happy_dzh.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ltg263.happy_dzh.R;
import com.ltg263.happy_dzh.activity.base.BaseActivity;
import com.ltg263.happy_dzh.adapter.LeftMenuAdapter;
import com.ltg263.happy_dzh.bean.ContentModel;
import com.ltg263.happy_dzh.fragment.MainFirstFragment;
import com.ltg263.happy_dzh.fragment.MainSecondFragment;
import com.ltg263.happy_dzh.fragment.MainThirdFragment;
import com.ltg263.happy_dzh.utils.IntentUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 * 描述：App主架构
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_title_bar)
    TextView tvTitleBar;
    @BindView(R.id.iv_right_menu)
    ImageView ivRightMenu;
    @BindView(R.id.iv_left_menu)
    ImageView ivLeftMenu;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.fragment_layout)
    RelativeLayout fragmentLayout;
    @BindView(R.id.dl_man_theme)
    DrawerLayout dlManTheme;
    @BindView(R.id.left_listview)
    ListView leftListview;
    @BindView(R.id.ll_bnt_1)
    LinearLayout llBnt1;
    @BindView(R.id.ll_bnt_2)
    LinearLayout llBnt2;
    @BindView(R.id.ll_bnt_3)
    LinearLayout llBnt3;
    @BindView(R.id.iv_main_1)
    ImageView ivMain1;
    @BindView(R.id.tv_main_1)
    TextView tvMain1;
    @BindView(R.id.iv_main_2)
    ImageView ivMain2;
    @BindView(R.id.tv_main_2)
    TextView tvMain2;
    @BindView(R.id.iv_main_3)
    ImageView ivMain3;
    @BindView(R.id.tv_main_3)
    TextView tvMain3;
    private List<ContentModel> list;
    private MainFirstFragment fragment_1;
    private MainSecondFragment fragment_2;
    private MainThirdFragment fragment_3;
    private static final int MIAN_PAGE_INDEX_1 = 0;
    private static final int MIAN_PAGE_INDEX_2 = 1;
    private static final int MIAN_PAGE_INDEX_3 = 2;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setLeftLvListener();
        showFragment(fragment_1,MIAN_PAGE_INDEX_1);

    }

    @Override
    protected void initData() {
        setLeftLvDate();
        showFragment(fragment_1,MIAN_PAGE_INDEX_1);
        // 找到底部菜单的按钮并设置监听
    }


    @OnClick({R.id.iv_right_menu, R.id.iv_left_menu, R.id.ll_bnt_1, R.id.ll_bnt_2, R.id.ll_bnt_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_right_menu:
//                dlManTheme.openDrawer(Gravity.RIGHT);
                break;
            case R.id.iv_left_menu:
//                dlManTheme.openDrawer(Gravity.LEFT);
                break;
            case R.id.ll_bnt_1:
                showFragment(fragment_1, MIAN_PAGE_INDEX_1);
                break;
            case R.id.ll_bnt_2:
                showFragment(fragment_2, MIAN_PAGE_INDEX_2);
                break;
            case R.id.ll_bnt_3:
                showFragment(fragment_3, MIAN_PAGE_INDEX_3);
                break;
        }
    }


    private void showFragment(Fragment fragment, int index) {
        if (fragment == null) {
            switch (index) {
                case MIAN_PAGE_INDEX_1:
                    fragment_1 = new MainFirstFragment();
                    fragment = fragment_1;
                    break;
                case MIAN_PAGE_INDEX_2:
                    fragment_2 = new MainSecondFragment();
                    fragment = fragment_2;
                    break;
                case MIAN_PAGE_INDEX_3:
                    fragment_3 = new MainThirdFragment();
                    fragment = fragment_3;
                    break;
            }

            // 判断当前界面是否隐藏，如果隐藏就进行添加显示，false表示显示，true表示当前界面隐藏
            if (!fragment.isHidden()) {
                addFragment(fragment);
                showFragment(fragment);
            }
        } else {
            if (fragment.isHidden()) {
                showFragment(fragment);
            }
        }
        setIndex(index);
    }

    /**
     * 添加Fragment
     *
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.rl_main_fragment, fragment);
        ft.commit();
    }

    /**
     * 显示Fragment
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        // 设置Fragment的切换动画
//         ft.setCustomAnimations(R.anim.fade_in_left,R.anim.fade_in_left);
        // 判断页面是否已经创建，如果已经创建，那么就隐藏掉
        if (fragment_1 != null) {
            ft.hide(fragment_1);
        }
        if (fragment_2 != null) {
            ft.hide(fragment_2);
        }
        if (fragment_3 != null) {
            ft.hide(fragment_3);
        }
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * 设置选中状态：
     *
     * @param index 下标
     */
    private void setIndex(int index) {
        switch (index) {
            case MIAN_PAGE_INDEX_1:
                setSelected(true,false,false);
                break;
            case MIAN_PAGE_INDEX_2:
                setSelected(false,true,false);
                break;
            case MIAN_PAGE_INDEX_3:
                setSelected(false,false,true);
                break;
        }
    }
    private void setSelected(boolean state_1,boolean state_2,boolean state_3){
        ivMain1.setSelected(state_1);
        tvMain1.setSelected(state_1);
        ivMain2.setSelected(state_2);
        tvMain2.setSelected(state_2);
        ivMain3.setSelected(state_3);
        tvMain3.setSelected(state_3);
    }
    private void setLeftLvListener() {
        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        dlManTheme.closeDrawers();
                        IntentUtil.startActivity(NewsActivity.class);
                        break;
                    case 2:
                        dlManTheme.closeDrawers();
                        IntentUtil.startActivity(MovieTicketActivity.class);
                        break;
                }
            }
        });
    }

    private void setLeftLvDate() {
        list = new ArrayList<>();
        list.add(new ContentModel(R.drawable.doctoradvice2, "新闻", 1));
        list.add(new ContentModel(R.drawable.infusion_selected, "电影票", 2));
        list.add(new ContentModel(R.drawable.mypatient_selected, "图片", 3));
        list.add(new ContentModel(R.drawable.mywork_selected, "视频", 4));
        list.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖", 5));
        list.add(new ContentModel(R.drawable.personal_selected, "投票", 6));
        leftListview.setAdapter(new LeftMenuAdapter(this, list));
    }
}
