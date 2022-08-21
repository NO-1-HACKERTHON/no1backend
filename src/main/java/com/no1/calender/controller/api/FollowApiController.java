package com.no1.calender.controller.api;

import com.no1.calender.domain.Follow;
import com.no1.calender.repository.FollowRepository;
import com.no1.calender.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FollowApiController {

    private final FollowRepository followRepository;
    private final FollowService followService;

    @PostMapping("/follow/{toMemberId}")
    public Follow followMember(@PathVariable Long toMemberId, Authentication authentication) {
        return followService.save(authentication.getName(), toMemberId);
    }

    @DeleteMapping("/follow/{toMemberId}")
    public void unFollowMember(@PathVariable Long toMemberId, Authentication authentication) {
        Long id = followService.getFollowIdByFromEmailToId(authentication.getName(), toMemberId);
        followRepository.deleteById(id);
    }
}
