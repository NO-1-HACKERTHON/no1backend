package com.no1.calender.repository;

import com.no1.calender.domain.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByEmail(String email);

    Member findMemberByEmail(String email);

    Member findMemberByMemberId(Long toId);
}
