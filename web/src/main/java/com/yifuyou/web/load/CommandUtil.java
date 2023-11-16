package com.yifuyou.web.load;

import java.util.Random;

public class CommandUtil {

    public static String getRandomId(int bit) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

}
