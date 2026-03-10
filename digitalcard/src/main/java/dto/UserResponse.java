package com.soliner.digitalcard.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String avatarUrl;
    private String bio;
    private List<SocialLinkResponse> socialLinks;
    private List<ProjectResponse> projects;
    private List<SkillResponse> skills;
}