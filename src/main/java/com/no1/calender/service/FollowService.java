package com.no1.calender.service;

import com.no1.calender.domain.Follow;
import com.no1.calender.domain.member.Member;
import com.no1.calender.repository.FollowRepository;
import com.no1.calender.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Transactional
    public Follow save(String email, Long toMemberId) {
        Member fromMember = memberRepository.findMemberByEmail(email);
        Member toMember = memberRepository.findMemberByMemberId(toMemberId);

        return followRepository.save(Follow.builder()
                        .fromMember(fromMember)
                        .toMember(toMember)
                        .build());
    }

    @Transactional
    public long getFollowIdByFromEmailToId(String email, Long toId) {
        Member fromMember = memberRepository.findMemberByEmail(email);
        Member toMember = memberRepository.findMemberByMemberId(toId);

        Follow follow = followRepository.findFollowByFromMemberAndToMember(fromMember, toMember);

        if (follow != null) return follow.getId();
        else return -1;
    }
}
