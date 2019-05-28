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
    private final Long waitTime;

    public Consumer(Integer value, Long waitTime) {
        this.value = value;
        this.waitTime = waitTime;
    }


    public void run() {
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
