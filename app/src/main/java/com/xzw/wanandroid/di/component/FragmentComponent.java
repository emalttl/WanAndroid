package com.xzw.wanandroid.di.component;

import android.app.Activity;
import android.content.Context;

import com.xzw.wanandroid.di.module.FragmentModule;
import com.xzw.wanandroid.di.scope.ContextLife;
import com.xzw.wanandroid.di.scope.PerFragment;
import com.xzw.wanandroid.ui.fragment.HomeFragment;
import com.xzw.wanandroid.ui.fragment.KnowledgeHierarchyFragment;
import com.xzw.wanandroid.ui.fragment.NavigationFragment;
import com.xzw.wanandroid.ui.fragment.ProjectFragment;

import dagger.Component;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/4
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeFragment fragment);

    void inject(KnowledgeHierarchyFragment fragment);

    void inject(NavigationFragment fragment);

    //void inject(ProjectFragment fragment);
}
