package com.consumer.flink.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "flink")
public class FlinkJobProperties {
    private String stateBackend;
    private String checkpointsDir;
    private long checkpointInterval;
    private String restartStrategy;
    private int restartAttempts;
    private long restartDelay;
}
