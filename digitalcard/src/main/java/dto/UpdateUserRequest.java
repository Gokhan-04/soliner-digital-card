package com.soliner.digitalcard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String name;
    private String email;
    private String avatarUrl;
    private String bio;
}