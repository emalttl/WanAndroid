package com.xzw.wanandroid.ui.contract;

import com.xzw.wanandroid.base.BaseContract;
import com.xzw.wanandroid.bean.KnowledgeHierarchy;

import java.util.List;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/7
 */
public interface KnowledgeHierarchyContract {

    interface View extends BaseContract.BaseView {
        void setKnowledgeHierarchy(List<KnowledgeHierarchy> knowledgeHierarchyList);
    }

    interface Presenter extends BaseContract.BasePresenter<KnowledgeHierarchyContract.View> {
        void loadKnowledgeSystems();

        void refresh();
    }
}
