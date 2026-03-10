package com.soliner.digitalcard.service;

import com.soliner.digitalcard.dto.SocialLinkRequest;
import com.soliner.digitalcard.dto.SocialLinkResponse;
import com.soliner.digitalcard.entity.SocialLink;
import com.soliner.digitalcard.entity.User;
import com.soliner.digitalcard.repository.SocialLinkRepository;
import com.soliner.digitalcard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialLinkService {

    private final SocialLinkRepository socialLinkRepository;
    private final UserRepository userRepository;

    public SocialLinkService(SocialLinkRepository socialLinkRepository, UserRepository userRepository) {
        this.socialLinkRepository = socialLinkRepository;
        this.userRepository = userRepository;
    }

    public List<SocialLinkResponse> list(Long userId) {
        return socialLinkRepository.findByUserId(userId)
                .stream()
                .map(s -> SocialLinkResponse.builder()
                        .id(s.getId())
                        .platform(s.getPlatform())
                        .url(s.getUrl())
                        .build())
                .toList();
    }

    public SocialLinkResponse create(Long userId, SocialLinkRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        SocialLink link = SocialLink.builder()
                .platform(req.getPlatform())
                .url(req.getUrl())
                .user(user)
                .build();

        SocialLink saved = socialLinkRepository.save(link);

        return SocialLinkResponse.builder()
                .id(saved.getId())
                .platform(saved.getPlatform())
                .url(saved.getUrl())
                .build();
    }

    public void delete(Long userId, Long id) {
        SocialLink link = socialLinkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SocialLink not found: " + id));

        if (!link.getUser().getId().equals(userId)) {
            throw new RuntimeException("This social link does not belong to user: " + userId);
        }

        socialLinkRepository.delete(link);
    }
}