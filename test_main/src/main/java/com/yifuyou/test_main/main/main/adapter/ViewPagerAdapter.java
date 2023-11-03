package com.yifuyou.test_main.main.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yifuyou.test_main.main.main.fragment.BaseFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setSrc(List<BaseFragment> fragments) {
        mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
