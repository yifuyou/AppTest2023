package com.yifuyou.web.load;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolFactory {
    private static final ExecutorService p = Executors.newScheduledThreadPool(3);



    public static void postTask(LoadThread runnable) {
        p.execute(runnable.getRunnable());
    }

}
