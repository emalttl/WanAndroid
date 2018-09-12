package com.xzw.wanandroid.ui.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzw.wanandroid.R;
import com.xzw.wanandroid.base.BaseFragment;
import com.xzw.wanandroid.bean.Article;
import com.xzw.wanandroid.bean.Banner;
import com.xzw.wanandroid.event.LoginEvent;
import com.xzw.wanandroid.ui.adapter.ArticleAdapter;
import com.xzw.wanandroid.ui.contract.HomeContract;
import com.xzw.wanandroid.ui.presenter.HomePresenter;
import com.xzw.wanandroid.utils.GlideImageLoader;
import com.xzw.wanandroid.utils.MyToast;
import com.xzw.wanandroid.utils.RxBus;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomePresenter>
        implements HomeContract.View,
        ArticleAdapter.OnItemClickListener,
        ArticleAdapter.OnItemChildClickListener,
        ArticleAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rvHomeArticles)
    RecyclerView mRvHomeArticles;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    ArticleAdapter mArticleAdapter;
    private com.youth.banner.Banner mBannerAbs;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView(View view) {
        /*设置RecyclerView*/
        mRvHomeArticles.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvHomeArticles.setAdapter(mArticleAdapter);

        /*设置BannerHeadView*/
        View homeBannerHeadView = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_banner_head, null);
        mBannerAbs = homeBannerHeadView.findViewById(R.id.banner_ads);
        mArticleAdapter.addHeaderView(homeBannerHeadView);

        /*设置事件监听*/
        mArticleAdapter.setOnItemClickListener(this);
        mArticleAdapter.setOnItemChildClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mArticleAdapter.setOnLoadMoreListener(this, mRvHomeArticles);

        // 请求网络
        mPresenter.loadHomeData();

        RxBus.getInstance().toFlowable(LoginEvent.class)
                .subscribe(new Consumer<LoginEvent>() {
                    @Override
                    public void accept(LoginEvent loginEvent) throws Exception {
                        mPresenter.refresh();
                    }
                });
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setHomeBanners(final List<Banner> banners) {
        List<String> images = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (Banner banner : banners) {
            images.add(banner.getImagePath());
            titles.add(banner.getTitle());
        }
        // 加载 Banner 图
        mBannerAbs.setImages(images)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();

        mBannerAbs.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                MyToast.showShort("点击了" + banners.get(position));
            }
        });
    }

    @Override
    public void setHomeArticles(Article article, int loadType) {
        // 加载 Item
        setLoadDataResult(mArticleAdapter, mSwipeRefreshLayout, article.getDatas(), loadType);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        // TODO 子控件点击事件
        MyToast.showShort("onItemChildClick");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // TODO 条目点击事件，跳转页面
        MyToast.showShort("onItemClick");
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }
}
