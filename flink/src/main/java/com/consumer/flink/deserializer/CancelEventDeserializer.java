package com.consumer.flink.deserializer;

import com.consumer.flink.model.CancelEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class CancelEventDeserializer implements DeserializationSchema<CancelEvent> {

    private transient ObjectMapper objectMapper;

    @Override
    public CancelEvent deserialize(byte[] message) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        if (message == null || message.length == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(message, CancelEvent.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize message", e);
        }
    }

    @Override
    public boolean isEndOfStream(CancelEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<CancelEvent> getProducedType() {
        return TypeInformation.of(CancelEvent.class);
    }
}
