package com.consumer.flink.job;

import com.consumer.flink.deserializer.CancelEventDeserializer;
import com.consumer.flink.deserializer.PaymentEventDeserializer;
import com.consumer.flink.deserializer.RefundEventDeserializer;
import com.consumer.flink.model.CancelEvent;
import com.consumer.flink.model.PaymentEvent;
import com.consumer.flink.model.RefundEvent;
import com.consumer.flink.processor.CancelProcessor;
import com.consumer.flink.processor.PaymentProcessor;
import com.consumer.flink.processor.RefundProcessor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.flink.connector.kafka.source.KafkaSource;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class MultiTopicKafkaConsumerJob {

    private final StreamExecutionEnvironment env;
    private final PaymentProcessor paymentProcessor;
    private final CancelProcessor cancelProcessor;
    private final RefundProcessor refundProcessor;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${kafka.topic.payment}")
    private String paymentTopic;

    @Value("${kafka.topic.refund}")
    private String refundTopic;

    @Value("${kafka.topic.cancel}")
    private String cancelTopic;

    @PostConstruct
    public void start() throws Exception {
        log.info("Starting Multi-Topic Flink Kafka Consumer Job");

        // Payments Source
        KafkaSource<PaymentEvent> paymentSource = KafkaSource.<PaymentEvent>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(paymentTopic)
                .setGroupId(groupId)
                .setValueOnlyDeserializer(new PaymentEventDeserializer())
                .setStartingOffsets(OffsetsInitializer.earliest())
                .build();

        DataStream<PaymentEvent> paymentStream = env.fromSource(
                paymentSource,
                WatermarkStrategy.<PaymentEvent>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                        .withTimestampAssigner((event, timestamp) -> event.getTimestamp()),
                "Payment Source"
        ).name("Payment Source").uid("payment-source");

        // Cancels Source
        KafkaSource<CancelEvent> cancelSource = KafkaSource.<CancelEvent>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(cancelTopic)
                .setGroupId(groupId)
                .setValueOnlyDeserializer(new CancelEventDeserializer())
                .setStartingOffsets(OffsetsInitializer.earliest())
                .build();

        DataStream<CancelEvent> cancelStream = env.fromSource(
                cancelSource,
                WatermarkStrategy.<CancelEvent>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                        .withTimestampAssigner((event, timestamp) -> event.getTimestamp()),
                "Cancel Source"
        ).name("Cancel Source").uid("cancel-source");

        // Refunds Source
        KafkaSource<RefundEvent> refundSource = KafkaSource.<RefundEvent>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(refundTopic)
                .setGroupId(groupId)
                .setValueOnlyDeserializer(new RefundEventDeserializer())
                .setStartingOffsets(OffsetsInitializer.earliest())
                .build();

        DataStream<RefundEvent> refundStream = env.fromSource(
                refundSource,
                WatermarkStrategy.<RefundEvent>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                        .withTimestampAssigner((event, timestamp) -> event.getTimestamp()),
                "Refund Source"
        ).name("Refund Source").uid("refund-source");

        // Process
        paymentProcessor.process(paymentStream);
        cancelProcessor.process(cancelStream);
        refundProcessor.process(refundStream);

        // Execute the Flink job
        env.execute("Flink Kafka Consumer Job");
    }
}
