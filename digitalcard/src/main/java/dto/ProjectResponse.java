package com.soliner.digitalcard.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String link;
}