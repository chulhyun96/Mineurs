package com.newlecmineursprj.advice;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /*404에러용*/
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex, Model model) {
        ErrorResult result = new ErrorResult(
                ex, "죄송합니다.요청하신 페이지를 찾을 수 없습니다..",
                "방문하시려는 페이지의 주소가 잘못 입력되었거나,<br>" +
                                "페이지의주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.",
                HttpStatus.NOT_FOUND.value(), "/error/4xx"
        );
        model.addAttribute("error", result);
        return result.getViewName();
    }
    /*400에러용*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String noResourceFoundExHandler(NoResourceFoundException ex, Model model) {
        log.info("NoResourceFoundException: {}", ex);
        ErrorResult result = new ErrorResult(
                ex, "잘못된 요청입니다.",
                "에러페이지가 반복적으로 발생 할 경우 고객센터로 연락부탁드립니다.",
                HttpStatus.BAD_REQUEST.value(), "/error/4xx"
        );
        model.addAttribute("error", result);
        return result.getViewName();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public void duplicateKeyExHandler(DuplicateKeyException ex, HttpServletResponse response) throws IOException {
        //회원 가입 아이디 중복 시
        if (ex.getMessage().contains("membername")) {
            log.error("Member ID duplication {}", ex);
            ErrorResult memberDuplicatedError = new ErrorResult(ex, "이미 등록된 회원입니다.","");
            response.sendRedirect("/register?error=" + URLEncoder.encode(memberDuplicatedError.getMessage(), StandardCharsets.UTF_8));
            return;
        }
        //회원가입 이메일 중복 시
        log.error("Member ID duplication {}", ex);
        ErrorResult memberDuplicatedError = new ErrorResult(ex, "이미 등록된 이메일입니다.","");
        response.sendRedirect("/register?error=" + URLEncoder.encode(memberDuplicatedError.getMessage(), StandardCharsets.UTF_8));
    }
}

