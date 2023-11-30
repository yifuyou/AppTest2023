/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.web.loadPg;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yifuyou.web.R;
import com.yifuyou.web.databinding.LoadPgLayoutBinding;

@Route(path = "/web/loadPage/")
public class LoadPageActivity extends AppCompatActivity {

    LoadPgLayoutBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.load_pg_layout);

        dataBinding.loadingRc.setAdapter(new LoadRcAdapter(new Handler()));
        dataBinding.loadingRc.setLayoutManager(new LinearLayoutManager(this));
    }
}
