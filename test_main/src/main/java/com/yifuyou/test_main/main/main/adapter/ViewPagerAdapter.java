package com.yifuyou.test_main.main.main.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yifuyou.test_main.R;

import java.util.HashMap;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private HashMap<String, ImageView> mImageViews;
    private List<Fragment> mFragments;


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setSrc(HashMap<String, ImageView> imageViews, List<Fragment> fragments) {
        mImageViews = imageViews;
        mFragments = fragments;

        mImageViews.forEach((s, imageView) -> {
            imageView.setColorFilter(R.color.color_4);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


        });

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
