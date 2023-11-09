package com.yifuyou.test_main.main.main;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yifuyou.test_main.R;
import com.yifuyou.test_main.databinding.CommunityActivityBinding;
import com.yifuyou.test_main.main.main.adapter.CommunityActivityAdapter;

@Route(path = "/main/community")
public class CommunityActivity extends BaseActivity<CommunityActivityBinding>{

    @Autowired(name = "name")
    String name = "";

    @Override
    void initView() {
        ARouter.getInstance().inject(this);

        dataBinding.objectNameTv.setText(name);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        dataBinding.msgRv.setLayoutManager(layoutManager);
        dataBinding.msgRv.setAdapter(new CommunityActivityAdapter(name));
    }

    @Override
    int getTopColor() {
        return R.color.color_1;
    }

    @Override
    int getLayoutId() {
        return R.layout.community_activity;
    }
}
