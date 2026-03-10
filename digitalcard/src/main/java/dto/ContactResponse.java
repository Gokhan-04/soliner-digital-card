package com.soliner.digitalcard.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ContactResponse {
    private Long id;
    private String name;
    private String email;
    private String message;
    private LocalDateTime createdAt;
}