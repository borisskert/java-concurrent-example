package com.github.borisskert.concurrent;

import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();


    public static long randomShorterWaitTime() {
        return nextLong(250, 1000);
    }

    public static long randomLongerWaitTime() {
        return nextLong(1000, 4000);
    }

    private static long nextLong(int min, int max) {
        long randomLong = Math.abs(random.nextLong());
        randomLong = randomLong % (max - min);

        return randomLong + min;
    }
}
