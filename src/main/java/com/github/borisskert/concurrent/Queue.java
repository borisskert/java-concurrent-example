package com.github.borisskert.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Queue {

    private final ExecutionService executionService;

    @Autowired
    public Queue(ExecutionService executionService) {
        this.executionService = executionService;
    }

    public void queue(Runnable work) {
        executionService.execute(work);
    }

    public int size() {
        return executionService.queueSize();
    }
}
