package com.github.borisskert.concurrent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    @NotNull
    @Min(1)
    private Integer maxCpus;

    public Integer getMaxCpus() {
        return maxCpus;
    }

    public void setMaxCpus(Integer maxCpus) {
        this.maxCpus = maxCpus;
    }
}
