package io.npee.springwebbasic.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {

    private Long memberId;
    private String name;

}
