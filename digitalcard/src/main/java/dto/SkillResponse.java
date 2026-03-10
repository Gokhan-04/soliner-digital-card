package com.soliner.digitalcard.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SkillResponse {
    private Long id;
    private String name;
    private Integer level;
}