/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * info
 *
 * @author Administrator
 * @since 2023/11/27
 */
public class TagObj extends BaseObservable {
	private String tagName;

	@Bindable
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
		notifyChange();
	}
}
