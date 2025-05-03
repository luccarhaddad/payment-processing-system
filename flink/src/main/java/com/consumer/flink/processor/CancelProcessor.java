package com.consumer.flink.processor;

import com.consumer.flink.model.CancelEvent;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.springframework.stereotype.Component;

@Component
public class CancelProcessor {

    public void process(DataStream<CancelEvent> stream) {
        stream
                .keyBy(CancelEvent::getPaymentId)
                .map(event -> {
                    // TODO: Process cancel event
                    return event;
                })
                .uid("cancel-processor")
                .name("Cancel Processor");
    }
}