package com.xzw.wanandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.xzw.wanandroid.base.BaseActivity;
import com.xzw.wanandroid.ui.fragment.HomeFragment;
import com.xzw.wanandroid.ui.fragment.KnowledgeHierarchyFragment;
import com.xzw.wanandroid.ui.fragment.NavigationFragment;
import com.xzw.wanandroid.ui.fragment.ProjectFragment;
import com.xzw.wanandroid.utils.MyToast;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.frame_group)
    FrameLayout frameGroup;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView mNavView;

    private ISupportFragment[] mFragments = new ISupportFragment[4];

    private long mExitTime;
    private int preIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolbarTitle.setText(R.string.home_pager);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        initMainPage();

        initNavView();
    }

    /**
     * 初始化主界面
     */
    private void initMainPage() {
        ISupportFragment homeFragment = findFragment(HomeFragment.class);
        if (homeFragment == null) {
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = KnowledgeHierarchyFragment.newInstance();
            mFragments[2] = NavigationFragment.newInstance();
            mFragments[3] = ProjectFragment.newInstance();
            loadMultipleRootFragment(R.id.frame_group, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        } else {
            mFragments[0] = homeFragment;
            mFragments[1] = findFragment(KnowledgeHierarchyFragment.class);
            mFragments[2] = findFragment(NavigationFragment.class);
            mFragments[3] = findFragment(ProjectFragment.class);
        }
    }

    /**
     * 初始化侧滑菜单
     */
    private void initNavView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setDisplayHomeAsUpEnabled(true);// 显示返回按钮
            //actionBar.setHomeAsUpIndicator(R.drawable.me);// 替换返回按钮的图片
            actionBar.setHomeAsUpIndicator(R.drawable.ic_line_with_24dp);// 替换返回按钮的图片
        }

        /*
         * 侧滑菜单点击事件
         */
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_item_wan_android:
                        MyToast.showShort("玩Android");
                        break;
                    case R.id.nav_item_my_collect:
                        MyToast.showShort("收藏");
                        break;
                    case R.id.nav_item_setting:
                        MyToast.showShort("设置");
                        break;
                    case R.id.nav_item_about_us:
                        MyToast.showShort("关于我们");
                        break;
                    case R.id.nav_item_logout:
                        MyToast.showShort("退出登录");
                        break;
                }
                mDrawerLayout.closeDrawers();// 关闭侧滑菜单
                return true;
            }
        });
    }

    /**
     * 标题栏菜单点击事件
     *
     * @param item 菜单
     * @return 是否拦截事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 重写返回按钮的点击事件
            case android.R.id.home:// HomeAsUp 的按钮 id 永远都是 android.R.id.home
                mDrawerLayout.openDrawer(GravityCompat.START);// 保证这里的行为和 XML 一致
                break;
            case R.id.action_usage:
                MyToast.showShort("点击了 action_usage");
                break;
            case R.id.action_search:
                MyToast.showShort("点击了 action_search");
                break;
        }
        return true;
    }

    /**
     * 底部按钮点击事件
     *
     * @param item 底部按钮菜单
     * @return 拦截事件
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_main_pager:
                mToolbarTitle.setText(R.string.home_pager);// 设置标题栏 title
                showHideFragment(mFragments[0], mFragments[preIndex]);
                preIndex = 0;
                break;
            case R.id.tab_knowledge_hierarchy:
                mToolbarTitle.setText(R.string.knowledge_hierarchy);
                showHideFragment(mFragments[1], mFragments[preIndex]);
                preIndex = 1;
                break;
            case R.id.tab_navigation:
                mToolbarTitle.setText(R.string.navigation);
                showHideFragment(mFragments[2], mFragments[preIndex]);
                preIndex = 2;
                break;
            case R.id.tab_project:
                mToolbarTitle.setText(R.string.project);
                showHideFragment(mFragments[3], mFragments[preIndex]);
                preIndex = 2;
                break;
        }
        return true;
    }

    /**
     * 加载标题栏 view
     *
     * @param menu menu
     * @return 是否拦截
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    protected boolean showHomeAsUp() {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                MyToast.showShort(R.string.exit_system);
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
