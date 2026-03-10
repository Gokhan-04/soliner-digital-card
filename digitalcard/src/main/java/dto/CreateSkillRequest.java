package com.soliner.digitalcard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSkillRequest {
    private String name;
    private Integer level;
}