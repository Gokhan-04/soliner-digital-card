package com.soliner.digitalcard.controller;

import com.soliner.digitalcard.dto.ProjectRequest;
import com.soliner.digitalcard.dto.ProjectResponse;
import com.soliner.digitalcard.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> list(@PathVariable Long userId) {
        return projectService.list(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(@PathVariable Long userId, @Valid @RequestBody ProjectRequest req) {
        return projectService.create(userId, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        projectService.delete(userId, id);
    }
}