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
    private final ConsumerFactory consumerFactory;
    private final ApplicationProperties.WaitTimeProperties producerProperties;

    @Autowired
    public Producer(Queue queue, ConsumerFactory consumerFactory, ApplicationProperties properties) {
        this.queue = queue;
        this.consumerFactory = consumerFactory;
        producerProperties = properties.getProducer();
    }

    public void run() {
        int counter = 0;

        do {
            long waitTime = getWaitTime();
            waitTime(waitTime);

            queue.queue(consumerFactory.build());
            counter++;

            LOG.info("Waited {} ms. Added {} to queue. New queue size: {}", waitTime, counter, queue.size());
        } while (true);
    }

    private long getWaitTime() {
        return RandomUtil.nextLong(
                producerProperties.getMinWaitTime(),
                producerProperties.getMaxWaitTime()
        );
    }

    private void waitTime(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
