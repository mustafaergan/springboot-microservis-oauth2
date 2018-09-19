package com.mustafaergan.microservis.admin.repository;

import com.mustafaergan.microservis.admin.entity.MessageResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageResourceRepository extends JpaRepository<MessageResource, String> {
    Optional<MessageResource> findByMessageKey(String messageKey);
}
