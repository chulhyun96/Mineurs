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

@Slf4j
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
    public String reg(@Validated Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Member Reg validation error: {}", bindingResult);
            model.addAttribute("member", member);
            return "redirect:/register";
        }
        service.reg(member);
        return "redirect:/";
    }
}
