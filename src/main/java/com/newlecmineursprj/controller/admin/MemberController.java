package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("admin")
@Controller("adminMemberController")
public class MemberController {
    private static final String MEMBERS_VIEW = "/admin/members";
    @Autowired
    private MemberService service;

    @GetMapping("members")
    public String list(@RequestParam(required = false) String searchMethod
            , @RequestParam(defaultValue = "") String searchKeyword
            , Model model) {


        List<Member> list = service.getList(searchMethod, searchKeyword.trim());
        model.addAttribute("list", list);
        return MEMBERS_VIEW + "/list";
    }
}
