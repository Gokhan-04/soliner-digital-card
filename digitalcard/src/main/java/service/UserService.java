package com.soliner.digitalcard.service;

import com.soliner.digitalcard.dto.*;
import com.soliner.digitalcard.entity.User;
import com.soliner.digitalcard.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponseWithoutRelations)
                .toList();
    }

    public UserResponse createUser(CreateUserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .avatarUrl(request.getAvatarUrl())
                .bio(request.getBio())
                .build();

        User savedUser = userRepository.save(user);
        return mapToUserResponseWithoutRelations(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return mapToUserResponseWithRelations(user);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setBio(request.getBio());

        User updatedUser = userRepository.save(user);
        return mapToUserResponseWithRelations(updatedUser);
    }

    private UserResponse mapToUserResponseWithoutRelations(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .socialLinks(Collections.emptyList())
                .projects(Collections.emptyList())
                .skills(Collections.emptyList())
                .build();
    }

    private UserResponse mapToUserResponseWithRelations(User user) {
        List<SocialLinkResponse> socialLinks = user.getSocialLinks() == null
                ? Collections.emptyList()
                : user.getSocialLinks().stream()
                .map(link -> SocialLinkResponse.builder()
                        .id(link.getId())
                        .platform(link.getPlatform())
                        .url(link.getUrl())
                        .build())
                .toList();

        List<ProjectResponse> projects = user.getProjects() == null
                ? Collections.emptyList()
                : user.getProjects().stream()
                .map(project -> ProjectResponse.builder()
                        .id(project.getId())
                        .title(project.getTitle())
                        .description(project.getDescription())
                        .link(project.getLink())
                        .build())
                .toList();

        List<SkillResponse> skills = user.getSkills() == null
                ? Collections.emptyList()
                : user.getSkills().stream()
                .map(skill -> SkillResponse.builder()
                        .id(skill.getId())
                        .name(skill.getName())
                        .level(skill.getLevel())
                        .build())
                .toList();

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .socialLinks(socialLinks)
                .projects(projects)
                .skills(skills)
                .build();
    }
}