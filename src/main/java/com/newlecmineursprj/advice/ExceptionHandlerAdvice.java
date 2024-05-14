package com.newlecmineursprj.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ModelAndView numberFormatExHandler(Exception ex1) {
        log.error("Exception = {}",ex1);
        log.error("Exception Message = {}",ex1.getMessage());

        ModelAndView mv = new ModelAndView();
        mv.addObject("error", ex1.getMessage());
        mv.addObject("errorCode", HttpStatus.BAD_REQUEST.value());
        mv.setViewName("/error/404");
        return mv;
    }
}

