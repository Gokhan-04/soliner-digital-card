package com.soliner.digitalcard.controller;

import com.soliner.digitalcard.dto.SocialLinkRequest;
import com.soliner.digitalcard.dto.SocialLinkResponse;
import com.soliner.digitalcard.service.SocialLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/social-links")
public class SocialLinkController {

    private final SocialLinkService socialLinkService;

    public SocialLinkController(SocialLinkService socialLinkService) {
        this.socialLinkService = socialLinkService;
    }

    @GetMapping
    public List<SocialLinkResponse> list(@PathVariable Long userId) {
        return socialLinkService.list(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SocialLinkResponse create(@PathVariable Long userId, @RequestBody SocialLinkRequest req) {
        return socialLinkService.create(userId, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        socialLinkService.delete(userId, id);
    }
}