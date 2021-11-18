package com.codenrock.raifcib21.flashlight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageToUser {
    private final UUID id = UUID.randomUUID();
    private CommunicationType communicationType;
    private Collection<ChannelType> channelTypes;
    private boolean liked;
    private boolean disliked;
    private String message;
    private Collection<UUID> userIds;
    private Collection<UserType> userTypes;
    private Collection<SegmentType> segmentTypes;
    private Collection<UUID> companyIds;
}
