package com.yifuyou.test_main.main.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.ImmersionBar;
import com.yifuyou.test_main.R;
import com.yifuyou.test_main.main.main.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public static void start(Context context) {
        context.startActivity(new Intent(context,HomeActivity.class));
    }

    enum ItemName{
        首页, 发现, 通知, 我的
    }

    private List<Fragment> fragments;

    private HashMap<String, ImageView> imageViews;

    private ViewPagerAdapter adapter;
//
//    private NavigationController mNavigationController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(R.color.color_4)
                .navigationBarColor(R.color.color_4)
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init();
        initView();
        initFragment();
    }

    private void initView() {
        setContentView(R.layout.home_activity);
        imageViews = new HashMap<>();
        ImageView imgV1= findViewById(R.id.img_btn_home);
        imageViews.put(ItemName.首页.name(), imgV1);
        ImageView imgV2= findViewById(R.id.img_btn_find);
        imageViews.put(ItemName.发现.name(), imgV2);
        ImageView imgV3= findViewById(R.id.img_btn_notify);
        imageViews.put(ItemName.通知.name(), imgV3);
        ImageView imgV4= findViewById(R.id.img_btn_user);
        imageViews.put(ItemName.我的.name(), imgV4);

        ViewPager viewPager = findViewById(R.id.home_viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }
    private void initFragment() {
        fragments = new ArrayList<>();
        //通过ARouter 获取其他组件提供的fragment
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_HOME).navigation();
        Fragment communityFragment = (Fragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_COMMUNICAT).navigation();
        Fragment moreFragment = (Fragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_FIND).navigation();
        Fragment userFragment = (Fragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_USER).navigation();
        fragments.add(homeFragment);
        fragments.add(communityFragment);
        fragments.add(moreFragment);
        fragments.add(userFragment);
        adapter.setSrc(imageViews, fragments);
    }
}

