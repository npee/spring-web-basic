package io.npee.springwebbasic.web.api;

import io.npee.springwebbasic.domain.error.ErrorResult;
import io.npee.springwebbasic.domain.member.MemberDto;
import io.npee.springwebbasic.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class ApiExceptionV2Controller {

    @GetMapping("/members/{id}")
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
