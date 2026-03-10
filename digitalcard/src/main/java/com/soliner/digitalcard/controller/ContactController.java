package com.soliner.digitalcard.controller;

import com.soliner.digitalcard.dto.ContactRequest;
import com.soliner.digitalcard.dto.ContactResponse;
import com.soliner.digitalcard.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponse create(@Valid @RequestBody ContactRequest req) {
        return contactService.create(req);
    }
}