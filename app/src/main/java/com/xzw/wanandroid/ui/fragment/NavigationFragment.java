package com.xzw.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.xzw.wanandroid.R;
import com.xzw.wanandroid.base.BaseFragment;
import com.xzw.wanandroid.bean.NavigationListData;
import com.xzw.wanandroid.ui.contract.NavigationContract;
import com.xzw.wanandroid.ui.presenter.NavigationPresenter;

import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 * 导航
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout mNavigationTabLayout;
    @BindView(R.id.navigation_divider)
    View mNavigationDivider;
    @BindView(R.id.navigation_RecyclerView)
    RecyclerView mNavigationRV;
    @BindView(R.id.normal_view)
    LinearLayout mNormalView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

        mPresenter.getNavigationListData(true);
    }

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    public void setNavigationListData(final List<NavigationListData> navigationDataList) {
        mNavigationTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationDataList == null ? 0 : navigationDataList.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationDataList.get(position).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity, R.color.shallow_green), ContextCompat.getColor(_mActivity, R.color.shallow_grey))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
    }
}
