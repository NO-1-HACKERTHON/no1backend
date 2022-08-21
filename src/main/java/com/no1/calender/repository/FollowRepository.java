package com.no1.calender.repository;

import com.no1.calender.domain.Follow;
import com.no1.calender.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFromMemberAndToMember(Member fromMember, Member toMember);

}
