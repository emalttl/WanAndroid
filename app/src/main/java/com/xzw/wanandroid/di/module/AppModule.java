package com.xzw.wanandroid.di.module;

import android.content.Context;

import com.xzw.wanandroid.App;
import com.xzw.wanandroid.di.scope.ContextLife;
import com.xzw.wanandroid.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Description :
 * Author : XZW
 * Date : 2018/8/14
 */
@Module
public class AppModule {

    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return application.getApplicationContext();
    }
}
