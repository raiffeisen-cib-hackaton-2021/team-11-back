package com.codenrock.raifcib21.flashlight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageFromUser implements Cloneable {
    private UUID id;
    private SourceType sourceType;
    private ChannelType channelType;
    private boolean liked;
    private boolean disliked;
    private String message;
    private UUID userId;
    private UUID companyId;
    private LocalDateTime time = LocalDateTime.now();

    @Override
    public MessageFromUser clone() {
        return MessageFromUser.builder()
                .id(id)
                .sourceType(sourceType)
                .channelType(channelType)
                .liked(liked)
                .disliked(disliked)
                .message(message)
                .userId(userId)
                .companyId(companyId)
                .time(time)
                .build();
    }
}
