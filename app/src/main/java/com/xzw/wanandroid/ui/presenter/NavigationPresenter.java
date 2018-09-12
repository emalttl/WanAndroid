package com.xzw.wanandroid.ui.presenter;

import android.annotation.SuppressLint;

import com.xzw.wanandroid.base.BasePresenter;
import com.xzw.wanandroid.bean.DataResponse;
import com.xzw.wanandroid.bean.NavigationListData;
import com.xzw.wanandroid.net.ApiService;
import com.xzw.wanandroid.net.RetrofitManager;
import com.xzw.wanandroid.ui.contract.NavigationContract;
import com.xzw.wanandroid.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/8
 */
public class NavigationPresenter extends BasePresenter<NavigationContract.View>
        implements NavigationContract.Presenter {

    @Inject
    public NavigationPresenter() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void getNavigationListData(boolean isShowError) {
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getNavigationListData()
                .compose(RxSchedulers.<DataResponse<List<NavigationListData>>>applySchedulers())
                .compose(mView.<DataResponse<List<NavigationListData>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<NavigationListData>>>() {
                    @Override
                    public void accept(DataResponse<List<NavigationListData>> listDataResponse) throws Exception {
                        mView.setNavigationListData(listDataResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFailed(throwable.getMessage());
                    }
                });
    }
}
