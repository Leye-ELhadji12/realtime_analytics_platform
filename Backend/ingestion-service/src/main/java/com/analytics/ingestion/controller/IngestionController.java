package com.analytics.ingestion.controller;

import com.analytics.ingestion.dto.TelemetryEvent;
import com.analytics.ingestion.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class IngestionController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<String> ingestEvent(@RequestBody TelemetryEvent event) {
        kafkaProducerService.sendEvent(event);
        return ResponseEntity.accepted().body("Event ingested successfully");
    }
}
