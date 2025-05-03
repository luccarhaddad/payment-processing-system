package com.consumer.flink.deserializer;

import com.consumer.flink.model.PaymentEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentEventDeserializer implements DeserializationSchema<PaymentEvent> {

    private transient ObjectMapper objectMapper;

    @Override
    public PaymentEvent deserialize(byte[] message) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        if (message == null || message.length == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(message, PaymentEvent.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize message", e);
        }
    }

    @Override
    public boolean isEndOfStream(PaymentEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<PaymentEvent> getProducedType() {
        return TypeInformation.of(PaymentEvent.class);
    }
}
