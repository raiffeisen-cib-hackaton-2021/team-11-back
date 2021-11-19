package com.codenrock.raifcib21.flashlight.entity;

import com.codenrock.raifcib21.flashlight.model.CommunicationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;
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
    @Lob
    private String message;
    private String userIds;
    private String userTypes;
    private String segmentTypes;
    private String companyIds;
    private LocalDateTime time;
}
