package com.xzw.wanandroid.ui.presenter;

import android.annotation.SuppressLint;

import com.xzw.wanandroid.base.BasePresenter;
import com.xzw.wanandroid.bean.DataResponse;
import com.xzw.wanandroid.bean.KnowledgeHierarchy;
import com.xzw.wanandroid.net.ApiService;
import com.xzw.wanandroid.net.RetrofitManager;
import com.xzw.wanandroid.ui.contract.KnowledgeHierarchyContract;
import com.xzw.wanandroid.utils.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/7
 */
public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.View>
        implements KnowledgeHierarchyContract.Presenter {

    @Inject
    public KnowledgeHierarchyPresenter() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadKnowledgeSystems() {
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getKnowledgeHierarchy()
                .compose(RxSchedulers.<DataResponse<List<KnowledgeHierarchy>>>applySchedulers())
                .compose(mView.<DataResponse<List<KnowledgeHierarchy>>>bindToLife())
                .subscribe(new Consumer<DataResponse<List<KnowledgeHierarchy>>>() {
                    @Override
                    public void accept(DataResponse<List<KnowledgeHierarchy>> listDataResponse) throws Exception {
                        mView.setKnowledgeHierarchy(listDataResponse.getData());
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
        loadKnowledgeSystems();
    }
}
