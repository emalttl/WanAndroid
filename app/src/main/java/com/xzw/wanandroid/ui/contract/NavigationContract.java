package com.xzw.wanandroid.ui.contract;

import com.xzw.wanandroid.base.BaseContract;
import com.xzw.wanandroid.bean.NavigationListData;

import java.util.List;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/8
 */
public interface NavigationContract {

    interface View extends BaseContract.BaseView {
        void setNavigationListData(List<NavigationListData> navigationDataList);
    }

    interface Presenter extends BaseContract.BasePresenter<NavigationContract.View> {
        void getNavigationListData(boolean isShowError);
    }
}
