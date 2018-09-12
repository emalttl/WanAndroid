package com.xzw.wanandroid.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.xzw.wanandroid.di.scope.ContextLife;
import com.xzw.wanandroid.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/4
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }
}
