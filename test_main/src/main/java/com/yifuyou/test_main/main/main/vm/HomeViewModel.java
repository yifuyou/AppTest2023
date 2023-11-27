/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.vm;

import com.yifuyou.test_main.main.main.pojo.TagObj;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * info
 *
 * @author Administrator
 * @since 2023/11/24
 */
public class HomeViewModel extends ViewModel {

    private MutableLiveData<TagObj> data;

    public MutableLiveData<TagObj> getTagName() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    public void setTagName(String tagName) {
        if (data == null) {
            data = new MutableLiveData<>();
            data.setValue(new TagObj());
        }
        data.getValue().setTagName(tagName);
    }
}
