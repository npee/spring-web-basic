package io.npee.springwebbasic.domain.login;

import io.npee.springwebbasic.domain.member.Member;
import io.npee.springwebbasic.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> password.equals(member.getPassword()))
                .orElse(null);
    }
}
