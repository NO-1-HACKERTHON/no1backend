package com.no1.calender.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.no1.calender.domain.member.MemberHashTag;
import com.no1.calender.domain.member.Member;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    private List<MemberHashTag> memberHashTags;

    private Set<AuthorityDto> authorityDtoSet;

    public static MemberDto from(Member member) {
        if(member == null) return null;

        return MemberDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .memberHashTags(member.getMemberHashTags())
                .authorityDtoSet(member.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
