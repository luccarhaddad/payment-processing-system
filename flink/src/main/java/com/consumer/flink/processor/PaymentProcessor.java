package com.consumer.flink.processor;

import com.consumer.flink.model.PaymentEvent;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    public void process(DataStream<PaymentEvent> stream) {
        stream
                .keyBy(PaymentEvent::getPaymentId)
                .map(event -> {
                    // TODO: Process payment event
                    return event;
                })
                .uid("payment-processor")
                .name("Payment Processor");
    }
}
