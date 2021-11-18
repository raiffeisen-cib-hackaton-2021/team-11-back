package com.codenrock.raifcib21.flashlight.repository;

import com.codenrock.raifcib21.flashlight.entity.MessageFromUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageFromUserRepository extends JpaRepository<MessageFromUserEntity, UUID> {

}
