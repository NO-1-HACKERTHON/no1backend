package com.no1.calender.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberHashTagId;

    private String memberHashTag;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
