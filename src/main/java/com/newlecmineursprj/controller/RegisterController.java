package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequestMapping("register")
@RequiredArgsConstructor
@Controller
public class RegisterController {

    private final RegisterService service;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping
    public String reg(@Validated Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Member Reg validation error: {}", bindingResult);
            model.addAttribute("member", member);
            return "register";
        }
        final String WELCOME_MESSAGE = URLEncoder.encode("회원가입을 축하합니다!!! 안전한 로그인을 위해 재접속 해주세요.", StandardCharsets.UTF_8) ;
        Boolean registrationStatus = service.reg(member);
        log.info("Member Reg successfully: {}", registrationStatus);
        return "redirect:/login?successfully=" + WELCOME_MESSAGE;
    }
}
