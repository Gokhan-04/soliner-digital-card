package com.soliner.digitalcard.repository;

import com.soliner.digitalcard.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}