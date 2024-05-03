package com.newlecmineursprj.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("member")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("modify")
    public String modify(Model model,
            @AuthenticationPrincipal WebUserDetails webUserDetails) {

        long memberId = webUserDetails.getId();

        Member member = memberService.getById(memberId);

        model.addAttribute("member", member);
        return "member/modify";
    }

    @PostMapping("modify")
    public String postMethodName(@RequestBody String entity) {
        // TODO: process POST request

        return "redirect:/";
    }

}
