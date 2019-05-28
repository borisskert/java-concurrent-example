package com.github.borisskert.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Produces increasing integer numbers and add them as {@link Consumer}s into the {@link Queue}. The {@link Producer}
 * is the first item who gets added to the {@link Queue} and gets executed by the {@link ExecutionService}.
 */
@Component
public class Producer implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

    private final Queue queue;

    @Autowired
    public Producer(Queue queue) {
        this.queue = queue;
    }

    public void run() {
        int counter = 0;

        do {
            long waitTime = RandomUtil.randomShorterWaitTime();
            waitTime(waitTime);

            queue.queue(new Consumer(counter));
            counter++;

            LOG.info("Waited {} ms. Added {} to queue. New size: {}", waitTime, counter, queue.size());
        } while (true);
    }

    private void waitTime(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
