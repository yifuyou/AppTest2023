package com.yifuyou.web;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yifuyou.web.databinding.LoadPgLayoutBinding;
import com.yifuyou.web.load.LoadRcAdapter;

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
