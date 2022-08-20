package com.no1.calender.service;

import com.no1.calender.domain.calender.Calender;
import com.no1.calender.domain.member.Authority;
import com.no1.calender.domain.member.Member;
import com.no1.calender.dto.Member.MemberDto;
import com.no1.calender.exception.DuplicateMemberException;
import com.no1.calender.repository.MemberRepository;
import com.no1.calender.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDto signup(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Calender calender = Calender.builder()
                .likeCalender(null)
                .build();

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .nickname(memberDto.getNickname())
                .hashtags(null)
                .authorities(Collections.singleton(authority))
                .activated(true)
                .calender(calender)
                .build();

        return MemberDto.from(memberRepository.save(member));
    }

    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String email) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null));
    }
}
