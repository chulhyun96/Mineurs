package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("register")
@RequiredArgsConstructor
@Controller
public class RegisterController {

    private final RegisterService service;

    @GetMapping
    public String form() {
        return "register";
    }

    @PostMapping
    public String reg(Member member) {
        service.reg(member);

        return "redirect:/";
    }
}
