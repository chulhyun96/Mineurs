package com.newlecmineursprj.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String noResourceFoundExHandler(NoResourceFoundException ex, Model model) {
        ErrorResult result = new ErrorResult(
                ex,"잘못된 페이지 요청입니다.",
                HttpStatus.BAD_REQUEST.value(),"/error/4xx"
        );
        model.addAttribute("error", result);
        return result.getViewName();
    }
}

