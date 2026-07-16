package com.query_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class DashboardPushService {

    // SimpMessagingTemplate is provided by Spring to send messages to WebSocket topics
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * This is a temporary simulation method.
     * It pushes a random "active users" metric to the WebSocket topic every 2 seconds.
     * Later, this will read actual data from Redis!
     */
    @Scheduled(fixedRate = 2000)
    public void pushLiveStats() {
        // Mock data representing what we would get from Redis
        int mockActiveUsers = (int) (Math.random() * 500) + 100;
        String jsonPayload = String.format("{\"activeUsers\": %d, \"timestamp\": \"%s\"}", mockActiveUsers, java.time.Instant.now().toString());
        
        // Push to all Angular clients subscribed to "/topic/live-stats"
        messagingTemplate.convertAndSend("/topic/live-stats", jsonPayload);
        log.debug("Pushed live stats to /topic/live-stats");
    }
}
