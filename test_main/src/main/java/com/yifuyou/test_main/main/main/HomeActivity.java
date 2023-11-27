package com.yifuyou.test_main.main.main;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yifuyou.test_main.R;
import com.yifuyou.test_main.databinding.HomeActivityBinding;
import com.yifuyou.test_main.main.main.nav.MenuActions;
import com.yifuyou.test_main.main.main.nav.MenuUtil;
import com.yifuyou.test_main.main.main.utls.CommandUtil;
import com.yifuyou.test_main.main.main.vm.HomeViewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

@Route(path = "/main/home")
public class HomeActivity extends BaseActivity<HomeActivityBinding> {
    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    int getTopColor() {
        return R.color.color_icon_bg;
    }

    @Override
    int getLayoutId() {
        return R.layout.home_activity;
    }

    void initView() {
        CommandUtil.init(getApplication());
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.setTagName("");
        homeViewModel.getTagName().observe(this, vm -> dataBinding.setVm(homeViewModel));

        MenuUtil.setNavMenu(this, dataBinding.layoutNav, R.id.fragment_container_placeholder, 0, new MenuActions() {
            @Override
            public void onPageChange(int pageIndex) {
                homeViewModel.setTagName(FragmentItemName.values()[pageIndex].name());
                Log.i("TAG", "onPageChange: " + pageIndex + "   " + FragmentItemName.values()[pageIndex].name());
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
