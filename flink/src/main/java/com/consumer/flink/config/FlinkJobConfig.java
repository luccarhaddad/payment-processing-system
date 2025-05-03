package com.consumer.flink.config;

import org.apache.flink.configuration.CheckpointingOptions;
import org.apache.flink.configuration.StateBackendOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlinkConfig {

    @Bean
    public StreamExecutionEnvironment flinkEnvironment() {
        var configuration = new org.apache.flink.configuration.Configuration();
        configuration.set(StateBackendOptions.STATE_BACKEND, "hashmap");
        configuration.set(CheckpointingOptions.CHECKPOINT_STORAGE, "filesystem");
        configuration.set(CheckpointingOptions.CHECKPOINTS_DIRECTORY, "file:///tmp/flink-checkpoints");

        var env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);
        env.enableCheckpointing(10000);

        return env;
    }
}