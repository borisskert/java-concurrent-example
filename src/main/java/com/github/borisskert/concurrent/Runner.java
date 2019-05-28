package com.github.borisskert.concurrent;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {

    private final Producer producer;
    private final Queue queue;

    public Runner(Producer producer, Queue queue) {
        this.producer = producer;
        this.queue = queue;
    }

    public void run(ApplicationArguments args) throws Exception {
        queue.queue(producer);
    }
}
