package com.xzw.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzw.wanandroid.R;
import com.xzw.wanandroid.base.BaseFragment;
import com.xzw.wanandroid.bean.KnowledgeHierarchy;
import com.xzw.wanandroid.ui.adapter.KnowledgeHierarchyAdapter;
import com.xzw.wanandroid.ui.contract.KnowledgeHierarchyContract;
import com.xzw.wanandroid.ui.presenter.KnowledgeHierarchyPresenter;
import com.xzw.wanandroid.utils.MyToast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 知识体系
 */
public class KnowledgeHierarchyFragment extends BaseFragment<KnowledgeHierarchyPresenter>
        implements KnowledgeHierarchyContract.View,
        KnowledgeHierarchyAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rvKnowledgeSystems)
    RecyclerView mRvKnowledgeSystems;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    KnowledgeHierarchyAdapter mKnowledgeHierarchyAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_hierarchy;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /*设置RecyclerView*/
        mRvKnowledgeSystems.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvKnowledgeSystems.setAdapter(mKnowledgeHierarchyAdapter);

        /*设置事件监听*/
        mKnowledgeHierarchyAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        /*请求数据*/
        mPresenter.loadKnowledgeSystems();
    }

    public static KnowledgeHierarchyFragment newInstance() {
        return new KnowledgeHierarchyFragment();
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showFailed(String errorMsg) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setKnowledgeHierarchy(List<KnowledgeHierarchy> knowledgeHierarchyList) {
        mKnowledgeHierarchyAdapter.setNewData(knowledgeHierarchyList);
        // 加载数据时不刷新
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // TODO 条目点击事件，跳转页面
        MyToast.showShort("onItemClick");
    }
}
