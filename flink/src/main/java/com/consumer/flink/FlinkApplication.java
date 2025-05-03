package com.consumer.flink;

import com.consumer.flink.config.FlinkJobConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FlinkJobConfig.class)
public class FlinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlinkApplication.class, args);
	}
}
