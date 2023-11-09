package com.yifuyou.test_main.main.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    abstract void initView();

    abstract int getTopColor();

    abstract int getLayoutId();

    protected T dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(getTopColor())
                .navigationBarColor(getTopColor())
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init();

        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initView();
    }


}
