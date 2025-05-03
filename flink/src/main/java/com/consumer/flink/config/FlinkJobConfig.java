package com.consumer.flink.config;

import lombok.RequiredArgsConstructor;
import org.apache.flink.configuration.CheckpointingOptions;
import org.apache.flink.configuration.RestartStrategyOptions;
import org.apache.flink.configuration.StateBackendOptions;
import org.apache.flink.core.execution.CheckpointingMode;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class FlinkJobConfig {

    private final FlinkJobProperties flinkJobProperties;

    @Bean
    public org.apache.flink.configuration.Configuration flinkConfiguration() {
        var config = new org.apache.flink.configuration.Configuration();
        config.set(CheckpointingOptions.CHECKPOINTING_CONSISTENCY_MODE, CheckpointingMode.EXACTLY_ONCE);
        config.set(CheckpointingOptions.CHECKPOINTING_INTERVAL, Duration.ofMillis(flinkJobProperties.getCheckpointInterval()));
        config.set(StateBackendOptions.STATE_BACKEND, flinkJobProperties.getStateBackend());
        config.set(CheckpointingOptions.CHECKPOINTS_DIRECTORY, flinkJobProperties.getCheckpointsDir());
        config.set(RestartStrategyOptions.RESTART_STRATEGY, flinkJobProperties.getRestartStrategy());
        config.set(RestartStrategyOptions.RESTART_STRATEGY_FIXED_DELAY_ATTEMPTS, flinkJobProperties.getRestartAttempts());
        config.set(RestartStrategyOptions.RESTART_STRATEGY_FIXED_DELAY_DELAY, Duration.ofMillis(flinkJobProperties.getRestartDelay()));
        return config;
    }

    @Bean
    public StreamExecutionEnvironment flinkEnvironment(org.apache.flink.configuration.Configuration flinkConfig) {
        return StreamExecutionEnvironment.createLocalEnvironment(flinkConfig);
    }
}