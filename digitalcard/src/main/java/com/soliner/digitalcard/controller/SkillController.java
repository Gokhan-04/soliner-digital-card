package com.soliner.digitalcard.controller;

import com.soliner.digitalcard.dto.SkillRequest;
import com.soliner.digitalcard.dto.SkillResponse;
import com.soliner.digitalcard.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillResponse> list(@PathVariable Long userId) {
        return skillService.list(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillResponse create(@PathVariable Long userId, @Valid @RequestBody SkillRequest req) {
        return skillService.create(userId, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        skillService.delete(userId, id);
    }
}