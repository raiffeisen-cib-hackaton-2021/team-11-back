package com.codenrock.raifcib21.flashlight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageToUser implements Cloneable {
    private UUID id;
    private CommunicationType communicationType;
    private Collection<ChannelType> channelTypes;
    private String message;
    private Collection<UUID> userIds;
    private Collection<UserType> userTypes;
    private Collection<SegmentType> segmentTypes;
    private Collection<UUID> companyIds;
    private LocalDateTime time = LocalDateTime.now();

    @Override
    public MessageToUser clone() {
        return MessageToUser.builder()
                .id(id)
                .communicationType(communicationType)
                .channelTypes(channelTypes)
                .message(message)
                .userIds(userIds)
                .message(message)
                .userTypes(userTypes)
                .segmentTypes(segmentTypes)
                .time(time)
                .build();
    }
}
