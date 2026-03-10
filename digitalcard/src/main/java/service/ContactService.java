package com.soliner.digitalcard.service;

import com.soliner.digitalcard.dto.ContactRequest;
import com.soliner.digitalcard.dto.ContactResponse;
import com.soliner.digitalcard.entity.ContactMessage;
import com.soliner.digitalcard.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public ContactResponse create(ContactRequest req) {
        ContactMessage msg = ContactMessage.builder()
                .name(req.getName())
                .email(req.getEmail())
                .message(req.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        ContactMessage saved = contactMessageRepository.save(msg);

        return ContactResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .message(saved.getMessage())
                .createdAt(saved.getCreatedAt())
                .build();
    }
}