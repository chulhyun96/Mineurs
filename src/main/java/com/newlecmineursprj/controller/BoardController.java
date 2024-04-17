package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.PostView;
import com.newlecmineursprj.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final PostService service;

    @GetMapping("notice")
    public String notice(Model model) {
        List<PostView> list = service.getList();
        model.addAttribute("list", list);
        return "/board/notice/list";
    }
}
