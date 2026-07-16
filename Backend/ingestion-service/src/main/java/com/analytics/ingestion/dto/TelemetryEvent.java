package com.analytics.ingestion.dto;

import lombok.Data;
import java.util.Map;

@Data
public class TelemetryEvent {
    private String eventId;
    private String timestamp;
    private String eventType;
    private String sessionId;
    private String userId;
    private Map<String, Object> context;
    private Map<String, Object> metrics;
    private Map<String, Object> customProperties;
}
