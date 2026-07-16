package com.analytics.ingestion.service;

import com.analytics.ingestion.dto.TelemetryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "web-events";

    public void sendEvent(TelemetryEvent event) {
        log.info("Sending event {} to Kafka topic: {}", event.getEventId(), TOPIC);
        kafkaTemplate.send(TOPIC, event.getEventId(), event);
    }
}
