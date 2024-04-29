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

@RequestMapping("member")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("modify")
    public String edit(Model model,
            @AuthenticationPrincipal WebUserDetails webUserDetails) {

        long memberId = webUserDetails.getId();

        Member member = memberService.getById(memberId);

        model.addAttribute("member", member);
        return "member/modify";
    }

}
