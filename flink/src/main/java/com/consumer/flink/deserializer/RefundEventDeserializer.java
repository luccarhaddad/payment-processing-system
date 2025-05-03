package com.consumer.flink.deserializer;

import com.consumer.flink.model.RefundEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class RefundEventDeserializer implements DeserializationSchema<RefundEvent> {

    private transient ObjectMapper objectMapper;

    @Override
    public RefundEvent deserialize(byte[] message) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        if (message == null || message.length == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(message, RefundEvent.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize message", e);
        }
    }

    @Override
    public boolean isEndOfStream(RefundEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<RefundEvent> getProducedType() {
        return TypeInformation.of(RefundEvent.class);
    }
}
