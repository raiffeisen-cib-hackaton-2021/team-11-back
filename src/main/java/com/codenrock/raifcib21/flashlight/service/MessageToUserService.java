package com.codenrock.raifcib21.flashlight.service;

import com.codenrock.raifcib21.flashlight.entity.MessageToUserEntity;
import com.codenrock.raifcib21.flashlight.model.ChannelType;
import com.codenrock.raifcib21.flashlight.model.MessageToUser;
import com.codenrock.raifcib21.flashlight.model.SegmentType;
import com.codenrock.raifcib21.flashlight.model.UserType;
import com.codenrock.raifcib21.flashlight.repository.MessageToUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.codenrock.raifcib21.flashlight.configuration.WebSocketConfiguration.DESTINATION;

@Service
@RequiredArgsConstructor
public class MessageToUserService {

    private static final String DELIMITER = ":";

    private final SimpMessagingTemplate template;

    private final MessageToUserRepository repository;

    public void send(MessageToUser message) {
        template.convertAndSend(DESTINATION+"/banner", message);
        template.convertAndSend(DESTINATION, message);
    }

    public void sendInitial(MessageToUser message) {
        template.convertAndSend(DESTINATION, message);
    }

    public MessageToUser persist(MessageToUser messageToUser) {
        var clone = messageToUser.clone();

        var persisted = repository.saveAndFlush(MessageToUserEntity.builder()
                .communicationType(clone.getCommunicationType())
                .channelTypes(clone.getChannelTypes().stream().map(ChannelType::name).collect(Collectors.joining(DELIMITER)))
                .message(clone.getMessage())
                .userIds(clone.getUserIds().stream().map(UUID::toString).collect(Collectors.joining(DELIMITER)))
                .userTypes(clone.getUserTypes().stream().map(UserType::name).collect(Collectors.joining(DELIMITER)))
                .segmentTypes(clone.getSegmentTypes().stream().map(SegmentType::name).collect(Collectors.joining(DELIMITER)))
                .companyIds(clone.getUserIds().stream().map(UUID::toString).collect(Collectors.joining(DELIMITER)))
                .time(clone.getTime())
                .build());
        clone.setId(persisted.getId());
        return clone;
    }

    public void sendInitial() {
        var initial = repository.findAll();
        if (initial.isEmpty()) {
            return;
        }
        initial.forEach(entity -> {
            var msg = MessageToUser.builder()
                    .id(entity.getId())
                    .communicationType(entity.getCommunicationType())
                    .channelTypes(Arrays.stream(entity.getChannelTypes().split(DELIMITER)).map(ChannelType::valueOf).collect(Collectors.toSet()))
                    .message(entity.getMessage())
                    .userIds(Collections.emptyList())
                    .segmentTypes(Collections.emptyList())
                    .companyIds(Arrays.stream(entity.getCompanyIds().split(DELIMITER)).map(UUID::fromString).collect(Collectors.toSet()))
                    .time(entity.getTime())
                    .build();
            var userIds = entity.getUserIds();
            if (!userIds.isEmpty()) {
                msg.setUserIds(Arrays.stream(userIds.split(",")).map(UUID::fromString).collect(Collectors.toSet()));
            }
            var companyIds = entity.getCompanyIds();
            if (!companyIds.isEmpty()) {
                msg.setCompanyIds(Arrays.stream(companyIds.split(",")).map(UUID::fromString).collect(Collectors.toSet()));
            }
            sendInitial(msg);
        });
    }
}
