package com.soliner.digitalcard.service;

import com.soliner.digitalcard.dto.SkillRequest;
import com.soliner.digitalcard.dto.SkillResponse;
import com.soliner.digitalcard.entity.Skill;
import com.soliner.digitalcard.entity.User;
import com.soliner.digitalcard.repository.SkillRepository;
import com.soliner.digitalcard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillService(SkillRepository skillRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public List<SkillResponse> list(Long userId) {
        return skillRepository.findByUserId(userId)
                .stream()
                .map(s -> SkillResponse.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .level(s.getLevel())
                        .build())
                .toList();
    }

    public SkillResponse create(Long userId, SkillRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Skill s = Skill.builder()
                .name(req.getName())
                .level(req.getLevel())
                .user(user)
                .build();

        Skill saved = skillRepository.save(s);

        return SkillResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .level(saved.getLevel())
                .build();
    }

    public void delete(Long userId, Long id) {
        Skill s = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found: " + id));

        if (!s.getUser().getId().equals(userId)) {
            throw new RuntimeException("This skill does not belong to user: " + userId);
        }

        skillRepository.delete(s);
    }
}