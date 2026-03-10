package com.soliner.digitalcard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

    @NotBlank(message = "Project title is required")
    private String title;

    private String description;
    private String link;
}