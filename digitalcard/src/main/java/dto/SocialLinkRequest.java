package com.soliner.digitalcard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialLinkRequest {

    @NotBlank(message = "Platform is required")
    private String platform;

    @NotBlank(message = "URL is required")
    private String url;
}