package com.xzw.wanandroid.ui.presenter;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.SPUtils;
import com.xzw.wanandroid.App;
import com.xzw.wanandroid.R;
import com.xzw.wanandroid.base.BasePresenter;
import com.xzw.wanandroid.bean.Article;
import com.xzw.wanandroid.bean.Banner;
import com.xzw.wanandroid.bean.DataResponse;
import com.xzw.wanandroid.bean.User;
import com.xzw.wanandroid.constant.Constant;
import com.xzw.wanandroid.constant.LoadType;
import com.xzw.wanandroid.net.ApiService;
import com.xzw.wanandroid.net.RetrofitManager;
import com.xzw.wanandroid.ui.contract.HomeContract;
import com.xzw.wanandroid.utils.RxSchedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/4
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private int mPage;
    private boolean isRefresh;

    @Inject
    public HomePresenter() {
        isRefresh = true;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadHomeBanners() {
        RetrofitManager.create(ApiService.class)
                .getHomeBanners()
                .compose(RxSchedulers.<DataResponse<List<Banner>>>applySchedulers())
                .compose(mView.<DataResponse<List<Banner>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<Banner>>>() {
                    @Override
                    public void accept(DataResponse<List<Banner>> listDataResponse) throws Exception {
                        mView.setHomeBanners(listDataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFailed(throwable.getMessage());
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadHomeArticles() {
        RetrofitManager.create(ApiService.class)
                .getHomeArticles(mPage)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> articleDataResponse) throws Exception {
                        int loadType = isRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                        mView.setHomeArticles(articleDataResponse.getData(), loadType);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFailed(throwable.getMessage());
                    }
                });
    }

    @Override
    public void refresh() {
        mPage = 0;
        isRefresh = true;
        loadHomeBanners();
        loadHomeArticles();
    }

    @Override
    public void loadMore() {
        mPage++;
        isRefresh = false;
        loadHomeArticles();
    }


    @SuppressLint("CheckResult")
    @Override
    public void loadHomeData() {
        mView.showLoading();
        String username = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.USERNAME_KEY);
        String password = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.PASSWORD_KEY);

        Observable<DataResponse<User>> login = RetrofitManager.create(ApiService.class).login(username, password);
        Observable<DataResponse<List<Banner>>> homeBanners = RetrofitManager.create(ApiService.class).getHomeBanners();
        Observable<DataResponse<Article>> homeArticles = RetrofitManager.create(ApiService.class).getHomeArticles(mPage);

        Observable.zip(login, homeBanners, homeArticles, new Function3<DataResponse<User>, DataResponse<List<Banner>>, DataResponse<Article>, Map<String, Object>>() {
            @Override
            public Map<String, Object> apply(DataResponse<User> userDataResponse, DataResponse<List<Banner>> listDataResponse, DataResponse<Article> articleDataResponse) throws Exception {
                Map<String, Object> map = new HashMap<>();
                map.put(Constant.USER_KEY, userDataResponse);
                map.put(Constant.BANNER_KEY, listDataResponse.getData());
                map.put(Constant.ARTICLE_KEY, articleDataResponse.getData());
                return map;
            }
        }).compose(RxSchedulers.<Map<String, Object>>applySchedulers()).compose(mView.<Map<String, Object>>bindToLife()).subscribe(new Consumer<Map<String, Object>>() {
            @Override
            public void accept(Map<String, Object> stringObjectMap) throws Exception {
                DataResponse<User> user = (DataResponse<User>) stringObjectMap.get(Constant.USER_KEY);
                if (user.getErrorCode() == 0) {
                    mView.showSuccess(App.getAppContext().getString(R.string.auto_login_in));
                } else {
                    mView.showFailed(String.valueOf(user.getErrorMsg()));
                }
                List<Banner> bannerList = (List<Banner>) stringObjectMap.get(Constant.BANNER_KEY);
                mView.setHomeBanners(bannerList);
                Article article = (Article) stringObjectMap.get(Constant.ARTICLE_KEY);
                mView.setHomeArticles(article, LoadType.TYPE_REFRESH_SUCCESS);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showFailed(throwable.getMessage());
            }
        });
    }
}
