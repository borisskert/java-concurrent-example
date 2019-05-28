package com.github.borisskert.concurrent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "app")
@Validated
public class ApplicationProperties {

    @NotNull
    @Valid
    private WaitTimeProperties producer;

    @NotNull
    @Valid
    private WaitTimeProperties consumer;

    @NotNull
    @Min(1)
    private Integer maxCpus;

    @NotNull
    @Min(1)
    private Integer minCpus;

    public Integer getMaxCpus() {
        return maxCpus;
    }

    public void setMaxCpus(Integer maxCpus) {
        this.maxCpus = maxCpus;
    }

    public Integer getMinCpus() {
        return minCpus;
    }

    public void setMinCpus(Integer minCpus) {
        this.minCpus = minCpus;
    }

    public WaitTimeProperties getProducer() {
        return producer;
    }

    public void setProducer(WaitTimeProperties producer) {
        this.producer = producer;
    }

    public WaitTimeProperties getConsumer() {
        return consumer;
    }

    public void setConsumer(WaitTimeProperties consumer) {
        this.consumer = consumer;
    }

    @Validated
    public static class WaitTimeProperties {
        @NotNull
        @Min(1)
        private Long minWaitTime;

        @NotNull
        @Min(1)
        private Long maxWaitTime;

        public Long getMinWaitTime() {
            return minWaitTime;
        }

        public void setMinWaitTime(Long minWaitTime) {
            this.minWaitTime = minWaitTime;
        }

        public Long getMaxWaitTime() {
            return maxWaitTime;
        }

        public void setMaxWaitTime(Long maxWaitTime) {
            this.maxWaitTime = maxWaitTime;
        }
    }
}
