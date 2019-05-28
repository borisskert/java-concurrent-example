package com.github.borisskert.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerFactory {

    private final ApplicationProperties.WaitTimeProperties consumerProperties;

    private int counter = 0;

    @Autowired
    public ConsumerFactory(ApplicationProperties properties) {
        consumerProperties = properties.getConsumer();
    }

    public Consumer build() {
        long waitTime = RandomUtil.nextLong(
                consumerProperties.getMinWaitTime(),
                consumerProperties.getMaxWaitTime()
        );

        Consumer consumer = new Consumer(counter, waitTime);

        counter++;

        return consumer;
    }
}
