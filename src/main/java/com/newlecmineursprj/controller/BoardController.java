package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService service;

    @GetMapping("notice")
    public String notice(Model model) {
        List<Board> list = service.getPostList();
        model.addAttribute("list", list);
        return "/board/notice/list";
    }
}
