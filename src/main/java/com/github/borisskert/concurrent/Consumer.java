package com.github.borisskert.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gets a {@link Integer} number and will be added into {@link Queue} by the {@link Producer}.
 * Gets executed the {@link ExecutionService}.
 */
public class Consumer implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);

    private final Integer value;

    public Consumer(Integer value) {
        this.value = value;
    }


    public void run() {
        long waitTime = RandomUtil.randomLongerWaitTime();
        waitTime(waitTime);

        LOG.info("Waited {} milliseconds, value: {}", waitTime, value);
    }

    private void waitTime(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
