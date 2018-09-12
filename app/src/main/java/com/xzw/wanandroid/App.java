package com.xzw.wanandroid;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.xzw.wanandroid.di.component.AppComponent;
import com.xzw.wanandroid.di.component.DaggerAppComponent;
import com.xzw.wanandroid.di.module.AppModule;

/**
 * Description : 程序的入口
 * Author : XZW
 * Date : 2018/8/14
 */
public class App extends Application {

    private static App instance;

    private AppComponent mApplicationComponent;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initAppComponent();

        Utils.init(this);

    }

    /**
     * 初始化 AppComponent
     */
    private void initAppComponent() {
        mApplicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

}
