package com.soliner.digitalcard.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocialLinkResponse {
    private Long id;
    private String platform;
    private String url;
}