package com.github.borisskert.concurrent;

import java.util.Random;

/**
 * Simple utility class to produce random long values within specified range.
 */
public class RandomUtil {

    private static final Random random = new Random();

    public static long nextLong(long min, long max) {
        long randomLong = Math.abs(random.nextLong());
        randomLong = randomLong % (max - min);

        return randomLong + min;
    }
}
