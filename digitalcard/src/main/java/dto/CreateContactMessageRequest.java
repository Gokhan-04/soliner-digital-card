package com.soliner.digitalcard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactMessageRequest {
    private String name;
    private String email;
    private String message;
}