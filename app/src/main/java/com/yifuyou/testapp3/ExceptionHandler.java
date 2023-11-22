/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.testapp3;

import androidx.annotation.NonNull;

/**
 * info
 *
 * @author Administrator
 * @since 2023/11/21
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        System.out.println("=================================================");

        System.out.println(t.getId() + " :  " + e.toString());
        System.out.println("=================================================");

    }
}
