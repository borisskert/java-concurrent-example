package com.github.borisskert.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Initializes Java's {@link ThreadPoolExecutor} and execute all item added into the {@link Queue}.
 */
@Service
public class ExecutionService {
    private static final Logger LOG = LoggerFactory.getLogger(ExecutionService.class);

    /**
     * The absolute minimum processor count is 2: one for the producer, and at least one for the consumer.
     */
    private static final int MIN_PROCESSOR_COUNT = 2;

    private final ThreadPoolExecutor executor;

    private final ApplicationProperties properties;

    @Autowired
    public ExecutionService(ApplicationProperties properties) {
        this.properties = properties;
        int useProcessors = estimateProcessors();

        LOG.info("Use processors: {}", useProcessors);

        executor = new ThreadPoolExecutor(
                useProcessors,
                useProcessors,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
    }

    public void execute(Runnable work) {
        executor.execute(work);
    }

    public int queueSize() {
        return executor.getQueue().size();
    }

    private int estimateProcessors() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        availableProcessors = Math.min(this.properties.getMaxCpus(), availableProcessors);
        availableProcessors = Math.max(availableProcessors, this.properties.getMinCpus());

        return Math.max(availableProcessors, MIN_PROCESSOR_COUNT);
    }
}
