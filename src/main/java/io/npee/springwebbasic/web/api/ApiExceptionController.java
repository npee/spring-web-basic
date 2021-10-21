package io.npee.springwebbasic.web.api;

import io.npee.springwebbasic.domain.member.MemberDto;
import io.npee.springwebbasic.exception.BadRequestException;
import io.npee.springwebbasic.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/api/response-status-ex1")
    public String responseStatsEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatsEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @GetMapping("/api/default-handler-ex")
    public String defaultException(@RequestParam Integer data) {
        return "ok";
    }

}
