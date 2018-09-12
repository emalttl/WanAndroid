package com.xzw.wanandroid.di.component;

import android.content.Context;

import com.xzw.wanandroid.di.module.AppModule;
import com.xzw.wanandroid.di.scope.ContextLife;
import com.xzw.wanandroid.di.scope.PerApp;

import dagger.Component;

/**
 * Description :
 * Author : XZW
 * Date : 2018/8/14
 */
@PerApp
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    Context getApplication();
}
