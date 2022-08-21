package com.no1.calender.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.no1.calender.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_member_id")
    @ManyToOne
    @JsonIgnore
    private Member fromMember;

    @JoinColumn(name = "to_member_id")
    @ManyToOne
    @JsonIgnore
    private Member toMember;

    @Builder
    public Follow(Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
    }
}
