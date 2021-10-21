package io.npee.springwebbasic.web.api;

import io.npee.springwebbasic.domain.member.MemberDto;
import io.npee.springwebbasic.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionController {

    public ApiExceptionController() {
    }

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") Long id) {
        if (id.equals(0L)) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id < 0) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id > 99) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }

}
