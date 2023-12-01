/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.fragment.home

import androidx.annotation.Keep


/**
 * info
 *
 * @author  Administrator
 * @since 2023/12/1
 */
@Keep
data class PowerDataItem(var name : String, var info : String) {
    constructor() : this("", "")

    lateinit var icon : String
    lateinit var url : String
    var type : String = "self"
}