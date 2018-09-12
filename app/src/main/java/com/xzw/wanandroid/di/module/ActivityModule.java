package com.xzw.wanandroid.di.module;

import android.app.Activity;
import android.content.Context;

import com.xzw.wanandroid.di.scope.ContextLife;
import com.xzw.wanandroid.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/4
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context providesActivityContext() {
        return activity;
    }

    @Provides
    @PerActivity
    public Activity providesActivity() {
        return activity;
    }
}
