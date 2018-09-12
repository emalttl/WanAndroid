package com.xzw.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.xzw.wanandroid.R;
import com.xzw.wanandroid.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment{


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView(View view) {

    }

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }
}
