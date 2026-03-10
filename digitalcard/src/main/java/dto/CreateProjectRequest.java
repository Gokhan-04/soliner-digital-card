package com.soliner.digitalcard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectRequest {
    private String title;
    private String description;
    private String link;
}