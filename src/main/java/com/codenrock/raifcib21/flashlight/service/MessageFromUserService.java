package com.codenrock.raifcib21.flashlight.service;

import com.codenrock.raifcib21.flashlight.entity.MessageFromUserEntity;
import com.codenrock.raifcib21.flashlight.model.MessageFromUser;
import com.codenrock.raifcib21.flashlight.repository.MessageFromUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageFromUserService {
    private final MessageFromUserRepository repository;

    public MessageFromUser persist(MessageFromUser messageFromUser) {
        var clone = messageFromUser.clone();

        var persisted = repository.save(MessageFromUserEntity.builder()
                .sourceType(clone.getSourceType())
                .channelType(clone.getChannelType())
                .liked(clone.isLiked())
                .disliked(clone.isDisliked())
                .message(clone.getMessage())
                .userId(clone.getUserId())
                .companyId(clone.getCompanyId())
                .build());
        clone.setId(persisted.getId());
        return clone;
    }
}
