package com.xzw.wanandroid.di.component;

import android.app.Activity;
import android.content.Context;

import com.xzw.wanandroid.di.module.ActivityModule;
import com.xzw.wanandroid.di.scope.ContextLife;
import com.xzw.wanandroid.di.scope.PerActivity;

import dagger.Component;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/4
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Application")
    Context getApplicationContext();

    @ContextLife("Activity")
    Context getActivityContext();

    Activity getActivity();
}
