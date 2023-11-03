package com.yifuyou.test_main.main.main;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.yifuyou.test_main.main.main.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class HomeActivity extends AppCompatActivity {
    public static void start(Context context) {
        context.startActivity(new Intent(context,HomeActivity.class));
    }

    private List<BaseFragment> fragments;

    private List<ImageView> imageViews;

    private ViewPagerAdapter adapter;

    private ViewPager viewPager;

    private int currentId;

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
    }

    private void initView() {
        setContentView(R.layout.home_activity);
        imageViews = new ArrayList<>();
        ImageView imgV1= findViewById(R.id.img_btn_home);
        imageViews.add(imgV1);
        ImageView imgV2= findViewById(R.id.img_btn_find);
        imageViews.add(imgV2);
        ImageView imgV3= findViewById(R.id.img_btn_notify);
        imageViews.add(imgV3);
        ImageView imgV4= findViewById(R.id.img_btn_user);
        imageViews.add(imgV4);

        imageViews.forEach(imageView-> imageView.setColorFilter(R.color.color_2));

        viewPager = findViewById(R.id.home_viewPager);
        initFragment();
        initViewPager();
        imgV1.setColorFilter(R.color.teal_200);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        //通过ARouter 获取其他组件提供的fragment
        BaseFragment homeFragment = (BaseFragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_HOME).navigation();
        BaseFragment moreFragment = (BaseFragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_FIND).navigation();
        BaseFragment communityFragment = (BaseFragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_COMMUNICAT).navigation();
        BaseFragment userFragment = (BaseFragment) ARouter.getInstance().build(FragmentRouterPath.Home.PAGE_USER).navigation();
        fragments.add(homeFragment);
        fragments.add(moreFragment);
        fragments.add(communityFragment);
        fragments.add(userFragment);
    }

    private void initViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.setSrc(fragments);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("TAG", "onPageSelected: " + position);
                if(currentId == position) {
                    return;
                }
                ImageView lastImageView = imageViews.get(currentId);
                if (lastImageView != null) {
                    lastImageView.setColorFilter(R.color.color_2);
                }
                currentId = position;
                ImageView imageView = imageViews.get(position);
                if (imageView != null) {
                    imageView.setColorFilter(R.color.teal_200);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("TAG", "onPageScrollStateChanged: " + state + "  " + viewPager.getCurrentItem());
            }
        });

        for (int position = 0; position < imageViews.size(); position++) {
            int finalPosition = position;
            imageViews.get(position).setOnClickListener(view -> viewPager.setCurrentItem(finalPosition));
        }
    }
}

