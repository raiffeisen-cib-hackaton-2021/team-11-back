package com.codenrock.raifcib21.flashlight.entity;

import com.codenrock.raifcib21.flashlight.model.ChannelType;
import com.codenrock.raifcib21.flashlight.model.SourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageFromUserEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;
    private String sourceId;
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;
    private boolean liked;
    private boolean disliked;
    private String message;
    private UUID userId;
    private UUID companyId;
    private LocalDateTime time;
}
