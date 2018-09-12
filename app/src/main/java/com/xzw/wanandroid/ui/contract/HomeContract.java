package com.xzw.wanandroid.ui.contract;

import com.xzw.wanandroid.base.BaseContract;
import com.xzw.wanandroid.bean.Article;
import com.xzw.wanandroid.bean.Banner;
import com.xzw.wanandroid.constant.LoadType;

import java.util.List;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/4
 */
public interface HomeContract {

    interface View extends BaseContract.BaseView {
        void setHomeBanners(List<Banner> banners);

        void setHomeArticles(Article article, @LoadType.checker int loadType);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadHomeBanners();

        void loadHomeArticles();

        void refresh();

        void loadMore();

        void loadHomeData();
    }
}
