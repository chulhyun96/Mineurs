package com.newlecmineursprj.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;


@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {
    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public void duplicateKeyExHandler(DuplicateKeyException ex, HttpServletResponse response) throws IOException {
        //회원 가입 아이디 중복 시
        if (ex.getMessage().contains("membername")) {
            log.error("Member ID duplication [{}]", ex);
            ErrorResult memberDuplicatedError = new ErrorResult(ex, "이미 등록된 회원입니다.");
            String errorMessage = URLEncoder.encode(memberDuplicatedError.getDefaultMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("/register?error=" + errorMessage);
            return;
        }
        //회원가입 이메일 중복 시
        log.error("Member Email duplication {}", ex);
        ErrorResult memberDuplicatedError = new ErrorResult(ex, "이미 등록된 이메일입니다.");
        response.sendRedirect("/register?error=" + URLEncoder.encode(memberDuplicatedError.getDefaultMessage(), StandardCharsets.UTF_8));
    }*/
}

