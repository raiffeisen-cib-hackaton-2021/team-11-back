package com.codenrock.raifcib21.flashlight.model.notification;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Notification {
    private UUID id;
    private LocalDateTime time;
    private NotificationType type;
    private String title;
    private String text;
}
