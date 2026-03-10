package com.soliner.digitalcard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSocialLinkRequest {
    private String platform;
    private String url;
}