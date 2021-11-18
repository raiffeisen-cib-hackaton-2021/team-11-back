package com.codenrock.raifcib21.flashlight.entity;

import com.codenrock.raifcib21.flashlight.model.ChannelType;
import com.codenrock.raifcib21.flashlight.model.CommunicationType;
import com.codenrock.raifcib21.flashlight.model.SegmentType;
import com.codenrock.raifcib21.flashlight.model.SourceType;
import com.codenrock.raifcib21.flashlight.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageToUserEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private CommunicationType communicationType;
    private String channelTypes;
    private String message;
    private String userIds;
    private String userTypes;
    private String segmentTypes;
    private String companyIds;
}
