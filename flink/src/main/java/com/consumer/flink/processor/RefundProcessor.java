package com.consumer.flink.processor;

import com.consumer.flink.model.RefundEvent;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.springframework.stereotype.Component;

@Component
public class RefundProcessor {

    public void process(DataStream<RefundEvent> stream) {
        stream
                .keyBy(RefundEvent::getPaymentId)
                .map(event -> {
                    // TODO: Process refund event
                    return event;
                })
                .uid("refund-processor")
                .name("Refund Processor");
    }
}
