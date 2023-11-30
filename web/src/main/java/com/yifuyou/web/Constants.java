/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Just for learn.
 */

package com.yifuyou.web;

public class Constants {
    // msg tag
    // 下载开始
    public static final int TAG_START_LOAD = 0x9101;

    // 下载进度
    public static final int TAG_LOADING_REPORT = 0x9102;

    // 下载完成
    public static final int TAG_LOADING_FINISH = 0x9108;

    public static final int STATE_INT_LOAD_SUCCESS = 1;
    public static final int STATE_INT_LOAD_FAILED = 2;
    public static final int STATE_INT_LOAD_UNFINISH = 3;

    public static final String STATE_STRING_LOAD_SUCCESS = "已完成";
    public static final String STATE_STRING_LOAD_FAILED = "下载失败";
    public static final String STATE_STRING_LOADING = "正在下载";
    public static final String STATE_STRING_LOAD_UNFINISH = "未下载完成";
}
