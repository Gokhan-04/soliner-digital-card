package com.soliner.digitalcard.service;

import com.soliner.digitalcard.dto.ProjectRequest;
import com.soliner.digitalcard.dto.ProjectResponse;
import com.soliner.digitalcard.entity.Project;
import com.soliner.digitalcard.entity.User;
import com.soliner.digitalcard.repository.ProjectRepository;
import com.soliner.digitalcard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<ProjectResponse> list(Long userId) {
        return projectRepository.findByUserId(userId)
                .stream()
                .map(p -> ProjectResponse.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .description(p.getDescription())
                        .link(p.getLink())
                        .build())
                .toList();
    }

    public ProjectResponse create(Long userId, ProjectRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Project p = Project.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .link(req.getLink())
                .user(user)
                .build();

        Project saved = projectRepository.save(p);

        return ProjectResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .link(saved.getLink())
                .build();
    }

    public void delete(Long userId, Long id) {
        Project p = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found: " + id));

        if (!p.getUser().getId().equals(userId)) {
            throw new RuntimeException("This project does not belong to user: " + userId);
        }

        projectRepository.delete(p);
    }
}